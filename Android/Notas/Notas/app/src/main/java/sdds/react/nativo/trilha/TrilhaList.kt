package sdds.react.nativo.trilha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_trilha_list.*
import sdds.react.nativo.Adapter.TrilhaButtonAdapter
import sdds.react.nativo.R

class TrilhaList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trilha_list)

        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpListeners(){

        btnCadastrar.setOnClickListener {
            val message = "Teste"

            val adapter = rcViewMessageList.adapter

            if (adapter is TrilhaButtonAdapter) {
                adapter.addItem(message)
                adapter.addAct(this, Intent(this, Trilha_View::class.java))
                rcViewMessageList.scrollToPosition(adapter.itemCount-1)
            }
        }

        fab_cadastrar_trilha.setOnClickListener {
            startActivity(Intent(this, Cadastro_trilha::class.java))
        }
    }

    private fun setUpRecyclerView(){
        val adapter = TrilhaButtonAdapter()

        //configuração de RecyclerView
        rcViewMessageList.layoutManager = LinearLayoutManager(this)
        rcViewMessageList.adapter = adapter

        if (adapter is TrilhaButtonAdapter) {
            adapter.addAct(this, Intent(this, Trilha_View::class.java))
            rcViewMessageList.scrollToPosition(adapter.itemCount-1)
        }
    }

}