package com.example.coronav19uk.MainApp.RegionsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronav19uk.R
import com.example.coronav19uk.Realm.Region
import io.realm.Realm


class RegionsFragment : Fragment() {

    var realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_regions, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.regions_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val results = realm.where(Region::class.java).findAll().sort("nhsRegionName")
        recyclerView.adapter = RegionsRealmRecyclerViewAdapter(context, results)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        realm.close()
    }
}
