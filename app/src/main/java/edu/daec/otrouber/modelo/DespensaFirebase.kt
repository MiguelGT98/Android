package edu.daec.otrouber.modelo

import android.util.Log
import com.google.firebase.database.*

class DespensaFirebase  {

    private lateinit var database: DatabaseReference
    private var mutableList: MutableList<Item> = mutableListOf<Item>()

    constructor(){
        database = FirebaseDatabase.getInstance().reference
        database.child("despensa").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mutableList.clear()
                val data = dataSnapshot!!.children
                data.forEach {
                    val item = it.getValue(Item::class.java)
                    mutableList.add(item!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun cargaFirebaseDummy(){
        val items: List<Item> = mutableListOf(
            Item("","Leche", 15 ),
            Item("","Pan", 1 ),
            Item("","Pasta", 15 ),
            Item("","Arroz", 3 ),
            Item("","Frijol", 5 )
        )
        items.forEach {
            val key = database.child("despensa").push().key
            it.id = key!!
            database.child("despensa").child(key!!).setValue(it)
        }

    }


    //Completa los siguientes métodos
    // y crea una pequeña interfaz en la app
    // para probar cada método.
    // Todas las salidas se ven en consola
    // Pueden usar: https://firebase.google.com/docs/database/android/start


    fun cargaUnItem(item: Item):Item{
        val key = database.child("despensa").push().key
        item.id = key!!
        database.child("despensa").child(key!!).setValue(item)

        return item

    }


    fun borraUnItem( item: Item){

    }

    fun borraTodo( ){

    }

    fun modificaUnItem( item: Item){

    }

    fun obtenTodos( ):MutableList<Item>{
        return mutableList
    }
}