package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.example.footballapp.model.FixturesResponse

data class FixturesState(val fixturesState: Async<FixturesResponse> = Uninitialized) : MvRxState
