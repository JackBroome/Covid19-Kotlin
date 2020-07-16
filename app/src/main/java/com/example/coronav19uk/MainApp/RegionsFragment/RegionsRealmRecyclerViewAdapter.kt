package com.example.coronav19uk.MainApp.RegionsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronav19uk.R
import com.example.coronav19uk.Realm.Region
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import kotlinx.android.synthetic.main.recycler_view_content_list.view.*

class RegionsRealmRecyclerViewAdapter(val context: Context?, val regions: RealmResults<Region>)
    : RealmRecyclerViewAdapter<Region, RegionsRealmRecyclerViewAdapter.LocationsViewHolder>(regions, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_content_list, parent, false))
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {

        val region = getItem(position)

        if (region != null) {
            holder.itemView.title_text_view.text = region.nhsRegionName
            holder.itemView.confirmed_cases_text_view.text = region.totalCases.toString()
        }
    }

    class LocationsViewHolder constructor(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return regions.size
    }
}