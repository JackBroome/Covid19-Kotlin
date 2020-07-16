package com.example.coronav19uk.Realm

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

enum class DailyIndicatorEnum(var value:String) {

    TOTAL_UK_CASES("Total UK Cases"),
    NEW_UK_CASES("New Cases"),
    RECOVERED("Recovered"),
    ENGLAND_CASES("England"),
    SCOTLAND_CASES("Scotland"),
    WALES_CASES("Wales"),
    NI_CASES("Northern Ireland")
}

open class DailyIndicator(

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("reported_date")
    var reportedDate: Date? = null,

    @SerializedName("total_uk")
    var totalUKCases: Int = 0,

    @SerializedName("total_uk_deaths")
    var totalUkDeaths: Int = 0,

    @SerializedName("new_uk")
    var newUKCases: Int = 0,

    @SerializedName("new_uk_deaths")
    var newUkDeaths: Int = 0,

    @SerializedName("england_cases")
    var englandCases: Int = 0,

    @SerializedName("england_deaths")
    var englandDeaths: Int = 0,

    @SerializedName("scotland_cases")
    var scotlandCases: Int = 0,

    @SerializedName("scotland_deaths")
    var scotlandDeaths: Int = 0,

    @SerializedName("wales_cases")
    var walesCases: Int = 0,

    @SerializedName("wales_deaths")
    var walesDeaths: Int = 0,

    @SerializedName("ni_cases")
    var niCases: Int = 0,

    @SerializedName("ni_deaths")
    var niDeaths: Int = 0

): RealmObject()