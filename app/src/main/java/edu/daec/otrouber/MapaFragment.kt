package edu.daec.otrouber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapa.*

/**
 * A simple [Fragment] subclass.
 */
class MapaFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var googleMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.setOnClickListener {
            view.findNavController().navigate(R.id.despensaFragment)
        }
        agregar.setOnClickListener {
            view.findNavController().navigate(R.id.altaFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            googleMap.setOnMarkerClickListener(this)

            val cdmx = LatLng(19.432608, -99.133209)
            googleMap.addMarker(
                MarkerOptions()
                .position(cdmx)
                .title("CDMX"))

            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    cdmx,
                    14f
                )
            )
        }


    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("Not yet implemented")
        findNavController().navigate(R.id.despensaFragment)
    }

}
