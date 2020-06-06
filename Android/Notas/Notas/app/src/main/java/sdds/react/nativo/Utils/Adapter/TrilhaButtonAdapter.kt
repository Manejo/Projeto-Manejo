package sdds.react.nativo.Utils.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import sdds.react.nativo.R
import sdds.react.nativo.Utils.Classes.TrilhaGetClass

class TrilhaButtonAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<String> = mutableListOf<String>()
    private lateinit var context: Context
    private lateinit var intent: Intent
    private val trilhas = mutableListOf<TrilhaGetClass>()

    fun addAct(context: Context, intent: Intent){
        this.context = context
        this.intent = intent
    }

    fun addTrilha(trilhaGet: TrilhaGetClass){
        this.trilhas.add(trilhaGet)
    }

    fun addItem(message: String){
        items.add(message)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.trilha_card_button, parent, false)

        return MessageViewHolder(card)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemAtual = items[position]

        if (holder is MessageViewHolder){
            holder.btnView.text = itemAtual
            holder.btnView.setOnClickListener(){

                trilhas.forEach {
                    if (it.nome.equals(itemAtual)){

                        intent.putExtra("trilhaNome", it.nome)
                        intent.putExtra("trilhaId", it.id)
                        intent.putExtra("trilhaCapacidade", it.capacidade)
                        intent.putExtra("trilhaDificuldade", it.dificuldade)
                        intent.putExtra("trilhaStatus", it.status)
                        intent.putExtra("trilhaCoordenadas", it.coordenadas)

                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnView: Button = itemView.findViewById(R.id.btnExibicao)
    }
}