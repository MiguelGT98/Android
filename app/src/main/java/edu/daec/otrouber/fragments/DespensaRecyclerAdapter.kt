package edu.daec.otrouber.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.daec.otrouber.R
import edu.daec.otrouber.modelo.DespensaFirebase
import edu.daec.otrouber.modelo.Item
import java.lang.Integer.parseInt


class DespensaRecyclerAdapter( private val list: List<Item>, private val context: Context)
    : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater, parent)
    }

    fun onLongClick(item: Item): Boolean {
        AlertDialog.Builder(context)
            .setTitle(item.descripcion)
            .setMessage("¿Estás seguro que deseas eliminarlo?")
            .setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialog, whichButton ->
                val despensaFirebase = DespensaFirebase();
                despensaFirebase.borraUnItem(item);
            })
            .setNegativeButton("Cancelar", null)
            .create()
            .show()

        return true;
    }

    fun onClick(item: Item): Boolean {
        val layout = LinearLayout(context)
        layout.setPadding(80,0,80,0)
        layout.orientation = LinearLayout.VERTICAL

        val inputText: EditText = EditText(context)
        inputText.setText(item.descripcion)
        layout.addView(inputText);

        val inputNumber: EditText = EditText(context)
        inputNumber.setInputType(InputType.TYPE_CLASS_NUMBER)
        inputNumber.setText("${item.cantidad}")
        layout.addView(inputNumber);

        AlertDialog.Builder(context)
            .setTitle("Editar ${item.descripcion}")
            .setView(layout)
            .setMessage("Ingresa los detalles abajo.")
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, whichButton ->
                    var editedItem: Item = Item(item.id, inputText.getText().toString(), parseInt(inputNumber.getText().toString()))
                    val despensaFirebase = DespensaFirebase();
                    despensaFirebase.modificaUnItem(editedItem)
                })
            .setNegativeButton("Cancelar", null)
            .create()
            .show()

        return true;
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Item = list[position]
        holder.bind(item)

        holder.itemView.setOnLongClickListener{onLongClick(item)}
        holder.itemView.setOnClickListener{onClick(item)}
    }

    override fun getItemCount(): Int = list.size

}

class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item, parent, false)) {
    private var cantidadItemTextView: TextView? = null
    private var itemDescripcionTextView: TextView? = null


    init {
        cantidadItemTextView = itemView.findViewById(R.id.item_cantidad)
        itemDescripcionTextView = itemView.findViewById(R.id.item_descipcion)
    }

    fun bind(item: Item) {
        Log.i("Error despensa", item.cantidad.toString())
        cantidadItemTextView?.text = item.cantidad.toString()
        itemDescripcionTextView?.text = item.descripcion
    }


}