package edu.daec.otrouber.modelo

import java.io.Serializable

data class Item(
    var id: String = "",
    var descripcion: String = "",
    var cantidad: Int = 0): Serializable
