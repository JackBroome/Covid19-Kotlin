package com.example.coronav19uk.Realm

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Region(

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("gss_code")
    var gssCode: String = "",

    @SerializedName("nhs_region_name")
    var nhsRegionName: String = "",

    @SerializedName("total_cases")
    var totalCases: Int = 0,

    @SerializedName("latitude")
    var latitude: Double = 0.0,

    @SerializedName("longitude")
    var longitude: Double = 0.0

): RealmObject()