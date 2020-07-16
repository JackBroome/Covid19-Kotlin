package com.example.coronav19uk.MainApp.LocationsFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronav19uk.R
import com.example.coronav19uk.Realm.Case
import io.realm.Realm
import io.realm.RealmResults

interface OnClickListener {

    fun clicked(caseID: Int)
}

class LocationsFragment : Fragment() {

    var realm:Realm = Realm.getDefaultInstance()

    var locationsRealmRecyclerViewAdapter: LocationsRealmRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_locations, container, false)

        val searchView = view.findViewById<SearchView>(R.id.location_search_view)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {

                var upperString = ""
                if (newText != null && newText != "" ) {
                    upperString = newText.substring(0, 1).toUpperCase() + newText.substring(1).toLowerCase()
                }

                val results = realm.where(Case::class.java).contains("gssName", upperString).findAll()

                updateLocationList(results)

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }
        })

        val recyclerView = view.findViewById<RecyclerView>(R.id.locations_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val results = realm.where(Case::class.java).findAll().sort("gssName")
        locationsRealmRecyclerViewAdapter = LocationsRealmRecyclerViewAdapter(context, results, object : OnClickListener {

            override fun clicked(caseID: Int) {
                startActivity(Intent(context, LocationDetail::class.java).putExtra("caseID", caseID))
            }
        })

        recyclerView.adapter = locationsRealmRecyclerViewAdapter

        return view
    }

    private fun updateLocationList(realmResults: RealmResults<Case>) {
        locationsRealmRecyclerViewAdapter?.updateData(realmResults)
        locationsRealmRecyclerViewAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}