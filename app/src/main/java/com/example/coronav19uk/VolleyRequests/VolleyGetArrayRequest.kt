package com.example.coronav19uk.VolleyRequests

import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class VolleyGetArrayRequest(
    url: String,
    listener: Response.Listener<JSONArray>,
    errorListener: Response.ErrorListener
) : JsonArrayRequest(Method.GET, url, null, listener, errorListener)






