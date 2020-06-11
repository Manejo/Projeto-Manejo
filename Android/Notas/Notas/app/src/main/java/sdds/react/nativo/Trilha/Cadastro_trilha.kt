package sdds.react.nativo.Trilha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.study.lmav.testandorecyclerview.Classes.NetworkUtils
import kotlinx.android.synthetic.main.activity_cadastro_trilha.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sdds.react.nativo.R
import sdds.react.nativo.Utils.Classes.TrilhaPostClass
import sdds.react.nativo.Utils.Interfaces.Endpoint
import java.util.*
import kotlin.concurrent.schedule

class Cadastro_trilha : AppCompatActivity() {

    protected var Contador: Int = 0
    protected val coordenadas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_trilha)


        setUpListeners()
    }

    private fun setUpListeners(){

        btnVoltar.setOnClickListener {
            startActivity(Intent(this, TrilhaList::class.java))
        }

        btnAddRotas.setOnClickListener{

            if (!txtX.text.equals("") && !txtY.text.equals("")){

                val coordenada: String = txtX.text.toString()  + "," + txtY.text.toString() + ";"
                coordenadas.add(coordenada)

                if (Contador < 4){
                    Contador++

                    txtContador.setText(Contador.toString())
                } else {
                    Toast.makeText(this, txtNome.text.toString() +
                        txtStatus.text.toString() +
                        txtDificuldade.text.toString() + coordenadas[0] + coordenadas[1] + coordenadas[2] +coordenadas[3], Toast.LENGTH_LONG).show()
                }
            }
        }

        btnAdicionar.setOnClickListener{
            if (Contador == 4) {

                val trilha = TrilhaPostClass(
                    40,
                    txtNome.text.toString(),
                    txtDificuldade.text.toString(),
                    txtStatus.text.toString(),
                    coordenadas[0] + coordenadas[1] + coordenadas[2] +coordenadas[3])

                postData(trilha)
            } else {
                Toast.makeText(this, "Não é possível finalizar o cadastro sem adicionar todas as 4 coordenadas", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun postData(trilhaPost: TrilhaPostClass) {

        val retrofitClient = NetworkUtils
            .getRetrofitInstance("http://192.168.1.64:3333")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.postTrilha(trilhaPost)

        callback.enqueue(object: Callback<TrilhaPostClass?>{
            override fun onResponse(call: Call<TrilhaPostClass?>, response: Response<TrilhaPostClass?>) {
                Toast.makeText(baseContext, "Trilha cadastrada com sucesso.", Toast.LENGTH_SHORT).show()

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
