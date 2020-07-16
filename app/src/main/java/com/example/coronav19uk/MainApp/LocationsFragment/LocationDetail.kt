package com.example.coronav19uk.MainApp.LocationsFragment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coronav19uk.R
import com.example.coronav19uk.Realm.Case
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import io.realm.Realm
import io.realm.kotlin.where


class LocationDetail : AppCompatActivity(), OnMapReadyCallback  {

    private var googleMap: GoogleMap? = null

    var caseID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        caseID = intent.getIntExtra("caseID", 0)

        val realm = Realm.getDefaultInstance()

        val case = realm.where<Case>().equalTo("id", caseID).findFirst()

        if (case != null) {
            findViewById<TextView>(R.id.location_detail_title).text = case.gssName
            findViewById<TextView>(R.id.location_detail_value).text = case.totalCases.toString()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        realm.close()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap

        val realm = Realm.getDefaultInstance()

        val case = realm.where<Case>().equalTo("id", caseID).findFirst()

        if (case != null) {
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(case.latitude, case.longitude), 10f))
        }

        realm.close()
    }
}