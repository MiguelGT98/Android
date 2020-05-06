package edu.daec.otrouber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.daec.otrouber.modelo.DespensaFirebase
import edu.daec.otrouber.modelo.Item

class MainActivity : AppCompatActivity() {

    private val despensaFirebase : DespensaFirebase = DespensaFirebase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //despensaFirebase.cargaFirebaseDummy()
    }

    fun agregaItem(view: View){
        despensaFirebase.cargaUnItem(
            Item("","Leche", 15 ))
    }

    fun cargaItems(view: View){
        despensaFirebase.obtenTodos()
    }

    fun borrarItems(view: View){
        despensaFirebase.borraTodo();
    }

    override fun onResume() {
        super.onResume()
    }
}
