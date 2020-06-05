package sdds.react.nativo.Initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*
import sdds.react.nativo.R
import sdds.react.nativo.Trilha.TrilhaList

class PrincipalAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        setOnClickListener()
    }

    private fun setOnClickListener(){
        btnTrilha.setOnClickListener{
            startActivity(Intent(this, TrilhaList::class.java))
        }
    }
}