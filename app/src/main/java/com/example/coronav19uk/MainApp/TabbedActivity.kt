package com.example.coronav19uk.MainApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coronav19uk.MainApp.LocationsFragment.LocationsFragment
import com.example.coronav19uk.MainApp.OverviewFragment.OverviewFragment
import com.example.coronav19uk.MainApp.RegionsFragment.RegionsFragment
import com.example.coronav19uk.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class TabbedActivity: AppCompatActivity() {

    private val locationsFragment: LocationsFragment = LocationsFragment()
    private val overviewFragment: OverviewFragment = OverviewFragment()
    private val regionsFragment: RegionsFragment = RegionsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val bottomNavigationView:BottomNavigationView = findViewById(R.id.bottom_nav_view)

        fun replaceFragment(newPage: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.navigation_bar_container, newPage)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction.commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navbar_overview -> {
                    replaceFragment(overviewFragment)
                }

                R.id.navbar_locations -> {
                    replaceFragment(locationsFragment)
                }

                R.id.navbar_regions -> {
                    replaceFragment(regionsFragment)
                }
            }
            true
        }
        replaceFragment(overviewFragment)
    }
}