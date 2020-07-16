package com.example.coronav19uk.MainApp.LocationsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronav19uk.R
import com.example.coronav19uk.Realm.Case
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import kotlinx.android.synthetic.main.recycler_view_content_list.view.*

class LocationsRealmRecyclerViewAdapter(val context: Context?, cases: RealmResults<Case>?, val listener: OnClickListener)
    : RealmRecyclerViewAdapter<Case, LocationsRealmRecyclerViewAdapter.LocationsViewHolder>(cases, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_content_list, parent, false))
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {

        val case = getItem(position)

        holder.itemView.title_text_view.text = case!!.gssName
        holder.itemView.confirmed_cases_text_view.text = case.totalCases.toString()

        holder.itemView.setOnClickListener {
            listener.clicked(case.id)
        }
    }

    class LocationsViewHolder constructor(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return data!!.size
    }
}