package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.example.footballapp.domain.models.MatchDomain

data class FixturesState(
    val fixturesState: Async<Map<String, List<MatchDomain>>> = Uninitialized,
    val currentDayFixturesState: Async<Map<String, List<MatchDomain>>> = Uninitialized,
    val allFixtures: Map<String, List<MatchDomain>> = emptyMap()
) : MvRxState
