package sdds.react.nativo.Mapa

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import sdds.react.nativo.R

class MapsAct : AppCompatActivity(), OnMapReadyCallback {

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

        val extras = intent.extras
        val coordenadas = extras?.getString("coordenadas")

        val partes = coordenadas.toString().split(";")

        val LatLng1 = partes[0].replace(";","")
        val LatLng2 = partes[1].replace(";","")
        val LatLng3 = partes[2].replace(";","")
        val LatLng4 = partes[3].replace(";","")

        /*
        val l1 = LatLng1.split(",")[0].replace(",", "")
        val l2 = LatLng1.split(",")[1].replace(",", "")


        mMap = googleMap

        val PEDI = LatLng(l1.toDouble(), l2.toDouble())
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
        )*/

        //Trilha
        mMap = googleMap

        var l1 = LatLng2.split(",")[0].replace(",", "").toDouble()
        var l2 = LatLng2.split(",")[1].replace(",", "").toDouble()
        val Trilha = LatLng(l1, l2)

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(Trilha, 15f)
        )

        l1 = LatLng1.split(",")[0].replace(",", "").toDouble()
        l2 = LatLng1.split(",")[1].replace(",", "").toDouble()
        val v1 = LatLng(l1, l2)

        l1 = LatLng2.split(",")[0].replace(",", "").toDouble()
        l2 = LatLng2.split(",")[1].replace(",", "").toDouble()
        val v2 = LatLng(l1, l2)

        l1 = LatLng3.split(",")[0].replace(",", "").toDouble()
        l2 = LatLng3.split(",")[1].replace(",", "").toDouble()
        val v3 = LatLng(l1, l2)

        l1 = LatLng4.split(",")[0].replace(",", "").toDouble()
        l2 = LatLng4.split(",")[1].replace(",", "").toDouble()
        val v4 = LatLng(l1, l2)

        val polylineOptions = PolylineOptions()
        polylineOptions.add(v1)
        polylineOptions.add(v2)
        polylineOptions.add(v3)
        polylineOptions.add(v4)
        polylineOptions.color(Color.RED)
        polylineOptions.width(5f)
        mMap.addPolyline(polylineOptions)
    }
}
