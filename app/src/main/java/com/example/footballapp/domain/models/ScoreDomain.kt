package com.example.footballapp.domain.models

data class ScoreDomain(
    val duration: String? = "",
    val extraTime: ExtraTimeDomain? = ExtraTimeDomain(),
    val fullTime: FullTimeDomain? = FullTimeDomain(),
    val halfTime: HalfTimeDomain? = HalfTimeDomain(),
    val penalties: PenaltiesDomain? = PenaltiesDomain(),
    val winner: String? = ""
)