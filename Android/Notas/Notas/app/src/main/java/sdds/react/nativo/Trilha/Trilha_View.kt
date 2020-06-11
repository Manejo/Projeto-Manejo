package sdds.react.nativo.Trilha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.study.lmav.testandorecyclerview.Classes.NetworkUtils
import kotlinx.android.synthetic.main.activity_trilha_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sdds.react.nativo.Initial.PrincipalAct
import sdds.react.nativo.Mapa.MapsAct
import sdds.react.nativo.R
import sdds.react.nativo.Utils.Classes.TrilhaPostClass
import sdds.react.nativo.Utils.Interfaces.Endpoint
import java.util.*
import kotlin.concurrent.schedule

class Trilha_View : AppCompatActivity() {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trilha_view)

        val extras = intent.extras

        if (extras != null) {
            val nome = extras.getString("trilhaNome")
            val status = extras.getString("trilhaStatus")
            val dificuldade = extras.getString("trilhaDificuldade")
            val coordenadas = extras.getString("trilhaCoordenadas")

            txtNome.setText(nome)
            txtStatus.setText(status)
            txtDificuldade.setText(dificuldade)
            txtCoordenadas.setText(coordenadas)

            setUpListeners()
        }
    }

    private fun setUpListeners(){
        fab_editarTrilha.setOnClickListener{
            val extras = intent.extras

            val id = extras?.getString("trilhaId")
            val trilha = TrilhaPostClass(
                40,
                txtNome.text.toString(),
                txtDificuldade.text.toString(),
                txtStatus.text.toString(),
                txtCoordenadas.text.toString())

            updateData(trilha, id.toString())
        }

        fab_apagarTrilha.setOnClickListener{
            val extras = intent.extras

            val id = extras?.getString("trilhaId")

            removeData(id.toString())
        }

        fab_voltar.setOnClickListener{
            startActivity(Intent(this, TrilhaList::class.java))
        }

        btnExibirMapa.setOnClickListener{
            startActivity(Intent(this, MapsAct::class.java).putExtra("coordenadas", txtCoordenadas.text.toString()))
        }
    }

    private fun updateData(trilhaPost: TrilhaPostClass, trilhaId: String) {

        val retrofitClient = NetworkUtils
            .getRetrofitInstance("http://192.168.1.64:3333")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.updateTrilha(trilhaId, trilhaPost)

        callback.enqueue(object: Callback<TrilhaPostClass?> {
            override fun onResponse(call: Call<TrilhaPostClass?>, response: Response<TrilhaPostClass?>) {
                Toast.makeText(baseContext, "Alterada com sucesso", Toast.LENGTH_SHORT).show()

                Timer("Voltar para lista", false).schedule(1000){
                    startActivity(Intent(baseContext, TrilhaList::class.java))
                }
            }

            override fun onFailure(call: Call<TrilhaPostClass?>, t: Throwable) {
                Toast.makeText(baseContext, "Alterada com sucesso", Toast.LENGTH_SHORT).show()

                Timer("Voltar para lista", false).schedule(1000){
                    startActivity(Intent(baseContext, TrilhaList::class.java))
                }
            }
        })
    }

    private fun removeData(trilhaId: String) {

        val retrofitClient = NetworkUtils
            .getRetrofitInstance("http://192.168.1.64:3333")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.apagaTrilha(trilhaId, trilhaId)

        callback.enqueue(object: Callback<TrilhaPostClass?> {
            override fun onResponse(call: Call<TrilhaPostClass?>, response: Response<TrilhaPostClass?>) {
                Toast.makeText(baseContext, "Removido com sucesso", Toast.LENGTH_SHORT).show()

                Timer("Voltar para lista", false).schedule(1000){
                    startActivity(Intent(baseContext, TrilhaList::class.java))
                }
            }

            override fun onFailure(call: Call<TrilhaPostClass?>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}
