package com.shaimaa.happyplacesapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shaimaa.happyplacesapp.R
import com.shaimaa.happyplacesapp.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_add_happy_place.*
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mHappyPlaceDetails : HappyPlaceModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            mHappyPlaceDetails = intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel
        }

        if(mHappyPlaceDetails != null){
            setSupportActionBar(toolbarMap)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = mHappyPlaceDetails!!.title
            toolbarMap.setNavigationOnClickListener {
                onBackPressed()
            }
            toolbarMap.setTitleTextColor(Color.WHITE)
            toolbarMap.setSubtitleTextColor(Color.WHITE)

            val supportMapFragment : SupportMapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

            supportMapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val position = LatLng(mHappyPlaceDetails!!.latitude,mHappyPlaceDetails!!.longitude)
        googleMap!!.addMarker(MarkerOptions().position(position).title(mHappyPlaceDetails!!.location))
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(position,15f)
        googleMap.animateCamera(newLatLngZoom)

    }
}