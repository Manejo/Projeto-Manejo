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
import sdds.react.nativo.Utils.Classes.TrilhaClass
import sdds.react.nativo.Utils.Interfaces.Endpoint

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
                    Log.i("teste", coordenadas[0] + coordenadas[1] + coordenadas[2] +coordenadas[3])
                }
            }
        }

        btnAdicionar.setOnClickListener{
            if (Contador == 4) {

                val trilha = TrilhaClass(
                    40,
                    "" + txtNome.text.toString(),
                    "" + txtDificuldade.text.toString(),
                    "" + txtStatus.text.toString(),
                    coordenadas[0] + coordenadas[1] + coordenadas[2] +coordenadas[3])

                Log.i("", trilha.nome)

                postData(trilha)
            } else {
                Toast.makeText(this, "Não é possível finalizar o cadastro sem adicionar todas as 4 coordenadas", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun postData(trilha: TrilhaClass) {
        Toast.makeText(baseContext, "Chegou aqui", Toast.LENGTH_SHORT).show()

        val retrofitClient = NetworkUtils
            .getRetrofitInstance("http://192.168.1.64:3333")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.postTrilha(trilha)

        callback.enqueue(object: Callback<TrilhaClass?>{
            override fun onResponse(call: Call<TrilhaClass?>, response: Response<TrilhaClass?>) {

            }

            override fun onFailure(call: Call<TrilhaClass?>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
