package com.example.footballapp.datasource.fixtures.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Area(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = ""
)