package com.example.footballapp.domain.models

data class SeasonDomain(
    val currentMatchday: Int? = 0,
    val endDate: String? = "",
    val id: Int? = 0,
    val startDate: String? = ""
)