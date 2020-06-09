package sdds.react.nativo.Initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*
import sdds.react.nativo.Mapa.MapsAct
import sdds.react.nativo.R
import sdds.react.nativo.Trilha.TrilhaList

class PrincipalAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setUpListeners()
    }

    private fun setUpListeners(){
        bt_trilhas.setOnClickListener{
            startActivity(Intent(this, TrilhaList::class.java))
        }
        bt_mapa.setOnClickListener{
            startActivity(Intent(this, MapsAct::class.java))
        }
        fab_info.setOnClickListener{
            startActivity(Intent(this, InfoAct::class.java))
        }
    }
}