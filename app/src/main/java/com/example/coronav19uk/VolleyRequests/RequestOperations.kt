package com.example.coronav19uk.VolleyRequests

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.coronav19uk.Configuration.APIEndpoint
import com.example.coronav19uk.Realm.Additional
import com.example.coronav19uk.Realm.Case
import com.example.coronav19uk.Realm.DailyIndicator
import com.example.coronav19uk.Realm.Region
import com.google.gson.Gson
import io.realm.Realm
import org.json.JSONArray

fun getDailyIndicators(context: Context) {

    val casesArrayRequest = VolleyGetArrayRequest(
        APIEndpoint.DAILY_INDICATORS.urlString(), Response.Listener { response: JSONArray ->

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction { realm ->

                for (i in 0 until response.length()) {
                    val jsonObject = response.get(i)

                    val dailyIndicator = Gson().fromJson(jsonObject.toString(), DailyIndicator::class.java)

                    realm.insertOrUpdate(dailyIndicator)
                }
            }

            realm.close()

            getAdditionals(context)

        }, Response.ErrorListener { response: VolleyError ->

            Log.e("Volley Error", response.toString())
        })

    VolleySingleton.getInstance(context).addToRequestQueue(casesArrayRequest)
}

fun getAdditionals(context: Context) {

    val casesArrayRequest = VolleyGetArrayRequest(
        APIEndpoint.ADDITIONALS.urlString(), Response.Listener { response: JSONArray ->

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction { realm ->

                for (i in 0 until response.length()) {
                    val jsonObject = response.get(i)

                    val addtional = Gson().fromJson(jsonObject.toString(), Additional::class.java)

                    realm.insertOrUpdate(addtional)
                }
            }

            realm.close()

            getCases(context)

        }, Response.ErrorListener { response: VolleyError ->

            Log.e("Volley Error", response.toString())
        })

    VolleySingleton.getInstance(context).addToRequestQueue(casesArrayRequest)
}

fun getCases(context: Context) {

    val casesArrayRequest = VolleyGetArrayRequest(
        APIEndpoint.CASES.urlString(), Response.Listener { response: JSONArray ->

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction { realm ->

                for (i in 0 until response.length()) {
                    val jsonObject = response.get(i)

                    val case = Gson().fromJson(jsonObject.toString(), Case::class.java)

                    realm.insertOrUpdate(case)
                }
            }

            realm.close()

            getRegions(context)

        }, Response.ErrorListener { response: VolleyError ->

            Log.e("Volley Error", response.toString())
        })

    VolleySingleton.getInstance(context).addToRequestQueue(casesArrayRequest)
}

fun getRegions(context: Context) {

    val casesArrayRequest = VolleyGetArrayRequest(
        APIEndpoint.REGIONS.urlString(), Response.Listener { response: JSONArray ->

            val realm = Realm.getDefaultInstance()

            realm.executeTransaction { transactionRealm ->

                for (i in 0 until response.length()) {
                    val jsonObject = response.get(i)

                    val region = Gson().fromJson(jsonObject.toString(), Region::class.java)

                    transactionRealm.insertOrUpdate(region)
                }
            }

            realm.close()

        }, Response.ErrorListener { response: VolleyError ->

            Log.e("Volley Error", response.toString())
        })

    VolleySingleton.getInstance(context).addToRequestQueue(casesArrayRequest)
}