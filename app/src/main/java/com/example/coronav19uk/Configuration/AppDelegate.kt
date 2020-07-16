package com.example.coronav19uk.Configuration

import android.app.Application
import com.example.coronav19uk.VolleyRequests.getCases
import com.example.coronav19uk.VolleyRequests.getDailyIndicators
import io.realm.Realm
import io.realm.RealmConfiguration

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder().name("coronav19uk.realm").build()
        Realm.setDefaultConfiguration(config)

        getDailyIndicators(this)
    }
}