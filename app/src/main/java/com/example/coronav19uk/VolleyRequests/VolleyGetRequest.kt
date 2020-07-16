package com.example.coronav19uk.VolleyRequests

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class VolleyGetRequest(
    url: String,
    listener: Response.Listener<JSONObject>,
    errorListener: Response.ErrorListener
) : JsonObjectRequest(Method.GET, url, null, listener, errorListener)






