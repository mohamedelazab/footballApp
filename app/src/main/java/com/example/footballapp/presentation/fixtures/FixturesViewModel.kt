package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.footballapp.datasource.fixtures.LazySchedulers
import com.example.footballapp.domain.usecase.fixtures.GetFixturesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FixturesViewModel @AssistedInject constructor(
    @Assisted initialState: FixturesState,
    private val getFixturesUseCase: GetFixturesUseCase,
    private val lazySchedulers: LazySchedulers,
) : BaseMvRxViewModel<FixturesState>(initialState) {

    init {
        getFixtures()
    }

    private fun getFixtures() {
        getFixturesUseCase.invoke()
            .subscribeOn(lazySchedulers.io())
            .execute { copy(fixturesState = it) }
    }

    companion object :
        MavericksViewModelFactory<FixturesViewModel, FixturesState> by hiltMavericksViewModelFactory()

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<FixturesViewModel, FixturesState> {
        override fun create(
            state: FixturesState,
        ): FixturesViewModel
    }
}