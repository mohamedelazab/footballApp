package com.example.footballapp.domain.models


data class CompetitionDomain(
    val area: AreaDomain? = AreaDomain(),
    val code: String? = "",
    val id: Int? = 0,
    val lastUpdated: String? = "",
    val name: String? = "",
    val plan: String? = ""
)