package edu.daec.otrouber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val cdmx = LatLng(19.432608, -99.133209)
        mMap.addMarker(MarkerOptions().position(cdmx).title("Marker in CDMX"))

        // Bodega Aurrera
        val aurrera = LatLng(19.4475288,-99.1845163)
        mMap.addMarker(MarkerOptions().position(aurrera).title("Bodega Aurrera"))

        // Superama
        val superama = LatLng(19.4403148,-99.1850013)
        mMap.addMarker(MarkerOptions().position(superama).title("Superama"))

        // La comer
        val comer = LatLng(19.4411262,-99.1821876)
        mMap.addMarker(MarkerOptions().position(comer).title("La Comer"))

        // Sams Club
        val sams = LatLng(19.4357022, -99.189715)
        mMap.addMarker(MarkerOptions().position(sams).title("Sams Club"))

        // Mercado Garda
        val garda = LatLng(19.4494912,-99.1865855)
        mMap.addMarker(MarkerOptions().position(garda).title("Mercado Garda"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aurrera, 14.0f))
    }
}
