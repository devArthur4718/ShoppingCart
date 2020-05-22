package com.devarthur4718.mvp.ui.location

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.mock.MockedData
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private val TAG = MapsActivity::class.java.simpleName
    private val REQUEST_LOCATION_PERMISSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        init()


    }

    private fun init() {
        input_search.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event?.action == KeyEvent.ACTION_DOWN
                    || event?.action == KeyEvent.KEYCODE_ENTER){

                    geoLocate()
                }
                return false
            }
        })

        ib_gps.setOnClickListener {
            getDeviceLocation()
        }

    }

    private fun geoLocate() {

        var search = input_search.text.toString()

        var gc = Geocoder(this)
        var address = gc.getFromLocationName(search, 1)

        if(address.size > 0){
            var currentAddress = address[0]
            Log.d(TAG, "geolocate: found location : $address")
//            Toast.makeText(this, "Location: $currentAddress", Toast.LENGTH_SHORT).show()
            var location = LatLng(currentAddress.latitude, currentAddress.longitude)
            moveCamera(location, DEFAULT_ZOOM, "")

        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        enableMyLocation()
    }

    private fun isPermissionGranted() : Boolean{
        return ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation(){
        if(isPermissionGranted()){
            mMap.isMyLocationEnabled = true
            getDeviceLocation()

        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }


    private fun moveCamera(location: LatLng, zoom : Float, title : String) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom))
        val markerOptions = MarkerOptions()
                                .position(location)
                                .title(title)
        mMap.addMarker(markerOptions)
    }

    private fun getDeviceLocation() {
        var locationClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation.addOnCompleteListener {task ->
            if(task.isSuccessful){
                var currentLocation = task.getResult()
                var position = LatLng(currentLocation!!.latitude,currentLocation!!.longitude)
                moveCamera(position, DEFAULT_ZOOM, "")
            }else{
                //do something
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_LOCATION_PERMISSION){
            if(grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                enableMyLocation()
            }
        }
    }

    companion object{
        const val DEFAULT_ZOOM = 15F
    }
}
