package com.example.footballapp.datasource.fixtures.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Odds(
    @Json(name = "msg")
    val msg: String? = ""
)