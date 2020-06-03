package sdds.react.nativo.Mapa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import sdds.react.nativo.R

class MapsAct : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)
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

        val PEDI = LatLng(-8.0034027, -34.9481667)
        mMap.addMarker(
            MarkerOptions()
                .position(PEDI)
                .title("Parque estadual Dois Irmãos")
                .icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.logodiscord)
                )
        )
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(PEDI, 14f)
        )

        //Trilha

        /*val Trilha = LatLng(-8.005402, -34.944379)

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(Trilha, 15f)
        )

        val v1 = LatLng(-8.009609, -34.943907)
        val v2 = LatLng(-8.006846, -34.948584)
        val v3 = LatLng(-8.005402, -34.944379)
        val v4 = LatLng(-8.002724, -34.950129)

        val polylineOptions = PolylineOptions()
        polylineOptions.add(v1)
        polylineOptions.add(v2)
        polylineOptions.add(v3)
        polylineOptions.add(v4)
        polylineOptions.color(Color.RED)
        polylineOptions.width(5f)
        mMap.addPolyline(polylineOptions)*/
    }
}
