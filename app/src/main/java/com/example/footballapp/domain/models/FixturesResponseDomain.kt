package com.example.footballapp.domain.models


data class FixturesResponseDomain(
    val competition: CompetitionDomain? = CompetitionDomain(),
    val count: Int? = 0,
    val filters: FiltersDomain? = FiltersDomain(),
    val matches: List<MatchDomain> = listOf()
)