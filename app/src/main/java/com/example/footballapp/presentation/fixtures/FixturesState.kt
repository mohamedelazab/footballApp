package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.example.footballapp.model.Match

data class FixturesState(
    val fixturesState: Async<Map<String, List<Match>>> = Uninitialized,
    val currentDayFixtures: Async<Map<String, List<Match>>> = Uninitialized
) : MvRxState
