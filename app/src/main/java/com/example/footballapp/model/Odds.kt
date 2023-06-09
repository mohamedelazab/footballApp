package com.example.footballapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Odds(
    @Json(name = "msg")
    val msg: String? = ""
)