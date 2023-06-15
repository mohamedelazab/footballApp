package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.example.footballapp.domain.models.MatchDomain

data class FixturesState(
    val fixturesState: Async<Map<String, List<MatchDomain>>> = Uninitialized,
    val favoritesState: Async<Map<String, List<MatchDomain>>> = Uninitialized,
    val currentDayFixturesState: Async<Map<String, List<MatchDomain>>> = Uninitialized,
    val allFixtures: Map<String, List<MatchDomain>> = emptyMap()
) : MvRxState {

    val isFixturesSuccess = currentDayFixturesState is Success && fixturesState is Success
    val isFavoritesSuccess = favoritesState is Success
    val isLoading = favoritesState is Loading || fixturesState is Loading
}
