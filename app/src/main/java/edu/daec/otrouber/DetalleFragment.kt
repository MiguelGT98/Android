package edu.daec.otrouber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.daec.otrouber.modelo.Item
import kotlinx.android.synthetic.main.fragment_detalle.*

/**
 * A simple [Fragment] subclass.
 */
class DetalleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item : Item = arguments?.getSerializable("item") as Item

        cantidad.text = item.cantidad.toString()

        descripcion.text = item.descripcion

    }
}
