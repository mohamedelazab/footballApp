package com.example.footballapp.datasource.fixtures.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Competition(
    @Json(name = "area")
    val area: Area? = Area(),
    @Json(name = "code")
    val code: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "lastUpdated")
    val lastUpdated: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "plan")
    val plan: String? = ""
)