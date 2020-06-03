package sdds.react.nativo.trilha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_trilha.*
import sdds.react.nativo.R

class Cadastro_trilha : AppCompatActivity() {

    protected var Contador: Int = 0

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
            if (Contador < 4){
                Contador++

                txtContador.setText(Contador.toString())
            }
        }
    }
}
