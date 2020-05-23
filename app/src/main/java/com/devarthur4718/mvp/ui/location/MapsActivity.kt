package com.devarthur4718.mvp.ui.location

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.devarthur4718.mvp.R
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
    private lateinit var gc: Geocoder
    private val TAG = MapsActivity::class.java.simpleName
    private val REQUEST_LOCATION_PERMISSION = 1
    private var selectedLocation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        gc = Geocoder(this)
        init()
        //TODO: we have to create a release key for MAPS api before updating it to Play Store.

    }

    private fun init() {
        input_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event?.action == KeyEvent.ACTION_DOWN
                    || event?.action == KeyEvent.KEYCODE_ENTER
                ) {

                    geoLocate()
                }
                return false
            }
        })

        ib_gps.setOnClickListener {
            getDeviceLocation()
        }

        btnConfirmLocation.setOnClickListener {
            setResult(Activity.RESULT_OK, intent.putExtra(SELECTED_LOCATION, selectedLocation))
            finish()
        }

    }

    private fun geoLocate() {

        var search = input_search.text.toString()
        input_search.setText("")
        input_search.setText(search)
        var address = gc.getFromLocationName(search, 1)

        if (address.size > 0) {
            var currentAddress = address[0]
            var title = "${address[0].thoroughfare}, ${address[0].subThoroughfare},${address[0].adminArea}"
            var location = LatLng(currentAddress.latitude, currentAddress.longitude)
            moveCamera(
                location,
                DEFAULT_ZOOM,
                "$title"
            )
            selectedLocation = title
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        enableMyLocation()
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
            getDeviceLocation()

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }


    private fun moveCamera(location: LatLng, zoom: Float, title: String) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom))
        val markerOptions = MarkerOptions()
            .position(location)
            .title(title)
        mMap.clear()
        mMap.addMarker(markerOptions)

    }

    private fun getDeviceLocation() {
        var locationClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var currentLocation = task.getResult()

                var position = currentLocation?.let { LatLng(it.latitude,it.longitude) }
                var address = position?.let { gc.getFromLocation(position.latitude, position.longitude,1)  }

                if (address?.size!! > 0) {
                    var currentAddress = address[0]
                    var title = "${address[0].thoroughfare}, ${address[0].subThoroughfare},${address[0].adminArea}"
                    var location = LatLng(currentAddress.latitude, currentAddress.longitude)
                    moveCamera(
                        location,
                        DEFAULT_ZOOM,
                        "$title"
                    )
                    selectedLocation = title
//                    input_search.setText(title)

                }
                if (position != null) {
                    moveCamera(position, DEFAULT_ZOOM, "")
                }
            } else {
                //do something
                getDeviceLocation()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    companion object {
        const val DEFAULT_ZOOM = 15F
        const val SELECTED_LOCATION = "SELECTED_LOCATION"
    }
}
