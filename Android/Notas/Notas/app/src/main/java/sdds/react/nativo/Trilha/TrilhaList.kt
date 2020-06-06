package sdds.react.nativo.Trilha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.lmav.testandorecyclerview.Classes.NetworkUtils
import com.study.lmav.testandorecyclerview.Classes.TrilhaDataClass
import kotlinx.android.synthetic.main.activity_trilha_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sdds.react.nativo.Utils.Adapter.TrilhaButtonAdapter
import sdds.react.nativo.R
import sdds.react.nativo.Utils.Classes.TrilhaGetClass
import sdds.react.nativo.Utils.Interfaces.Endpoint

class TrilhaList : AppCompatActivity() {

    protected val trilhas = mutableListOf<TrilhaGetClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trilha_list)

        getData()
        setUpListeners()
        setUpRecyclerView()
        iniciaTrilhas()
    }

    private fun iniciaTrilhas(){
        val adapter = rcViewMessageList.adapter

        trilhas.forEach {
            if (adapter is TrilhaButtonAdapter) {
                adapter.addItem(it.nome)
                adapter.addTrilha(it)
                adapter.addAct(this, Intent(this, Trilha_View::class.java))
                rcViewMessageList.scrollToPosition(adapter.itemCount-1)
            }
        }
    }

    private fun setUpListeners(){

        fab_cadastrar_trilha.setOnClickListener {
            startActivity(Intent(this, Cadastro_trilha::class.java))
        }
    }

    private fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("http://192.168.1.64:3333")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getTrilhas()

        callback.enqueue(object : Callback<List<TrilhaDataClass>> {
            override fun onFailure(call: Call<List<TrilhaDataClass>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<TrilhaDataClass>>, response: Response<List<TrilhaDataClass>>) {
                response.body()?.forEach {

                    val trilha = TrilhaGetClass(it.id, it.capacidade, it.nome, it.dificuldade, it.status, it.coordenadas)
                    //val trilha = TrilhaGetClass(it.capacidade, it.nome, it.dificuldade, it.status, it.coordenadas)

                    trilhas.add(trilha)
                }

                iniciaTrilhas()
            }
        })
    }

    private fun setUpRecyclerView(){
        val adapter = TrilhaButtonAdapter()

        //configuração de RecyclerView
        rcViewMessageList.layoutManager = LinearLayoutManager(this)
        rcViewMessageList.adapter = adapter

        adapter.addAct(this, Intent(this, Trilha_View::class.java))
        rcViewMessageList.scrollToPosition(adapter.itemCount-1)
    }
}