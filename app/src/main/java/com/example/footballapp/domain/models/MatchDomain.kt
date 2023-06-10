package com.example.footballapp.domain.models

data class MatchDomain(
    val awayTeam: AwayTeamDomain? = AwayTeamDomain(),
    val group: Any? = Any(),
    val homeTeam: HomeTeamDomain? = HomeTeamDomain(),
    val id: Int? = 0,
    val lastUpdated: String? = "",
    val matchday: Int? = 0,
    val odds: OddsDomain? = OddsDomain(),
    val referees: List<RefereeDomain?>? = listOf(),
    val score: ScoreDomain? = ScoreDomain(),
    val season: SeasonDomain? = SeasonDomain(),
    val stage: String? = "",
    val status: String? = "",
    val utcDate: String = "",
    val isFavoriteToUser: Boolean = false,
) {
    enum class Status(val value: String) {
        FINISHES("FINISHED")
    }
}