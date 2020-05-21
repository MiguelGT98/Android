package edu.daec.otrouber

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import edu.daec.otrouber.modelo.DespensaFirebase
import edu.daec.otrouber.modelo.Item
import kotlinx.android.synthetic.main.fragment_alta.*

/**
 * A simple [Fragment] subclass.
 */
class AltaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alta, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        send_button.setOnClickListener {
            val despensaFirebase = DespensaFirebase()
            val item = Item("", description_edittext.text.toString(),cantidad_edittext.text.toString().toInt() )
            despensaFirebase.cargaUnItem(item)
            context!!.hideKeyboard(it)
            }

    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
