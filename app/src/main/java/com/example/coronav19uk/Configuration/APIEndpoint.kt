package com.example.coronav19uk.Configuration

import com.example.coronav19uk.BuildConfig

enum class APIEndpoint(var endpoint:String) {

    DAILY_INDICATORS("cv_daily_indicators"),
    REGIONS("cv_nhs_regions"),
    ADDITIONALS("cv_additionals"),
    CASES("gss_cases");

    fun baseURL(): String {

        return if (BuildConfig.DEBUG) {
            "http://pinchtozoom.co.uk/api/" // Jack Work

        } else {
            "http://pinchtozoom.co.uk/api/" // Live
        }
    }

    fun urlString(): String {

        return baseURL() + this.endpoint
    }
}