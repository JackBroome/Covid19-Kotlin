package com.example.coronav19uk.Realm

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Additional(

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("recovered")
    var recovered: Int = 0,

    @SerializedName("deaths")
    var deaths: Int = 0

): RealmObject()