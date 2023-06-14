package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.footballapp.datasource.fixtures.LazySchedulers
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.usecase.fixtures.GetCurrentDayFixturesUseCase
import com.example.footballapp.domain.usecase.fixtures.GetFixturesUseCase
import com.example.footballapp.domain.usecase.fixtures.UpdateFavoritesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FixturesViewModel @AssistedInject constructor(
    @Assisted initialState: FixturesState,
    private val getFixturesUseCase: GetFixturesUseCase,
    private val getCurrentDayFixturesUseCase: GetCurrentDayFixturesUseCase,
    private val updateFavoritesUseCase: UpdateFavoritesUseCase,
    private val lazySchedulers: LazySchedulers,
) : BaseMvRxViewModel<FixturesState>(initialState) {

    init {
        logStateChanges()
        getFixtures()
    }

    fun getFixtures() {
        getFixturesUseCase.invoke()
            .subscribeOn(lazySchedulers.io())
            .doFinally { getCurrentDayFixtures() }
            .execute { copy(fixturesState = it) }
    }

    fun getCurrentDayFixtures() {
        withState {
            if (it.fixturesState !is Success) return@withState

            getCurrentDayFixturesUseCase.invoke((it.fixturesState)())
                .subscribeOn(lazySchedulers.io())
                .execute {
                    copy(
                        currentDayFixturesState = it,
                        allFixtures = (it)()?.plus(
                            (fixturesState)() ?: emptyMap()
                        ) ?: emptyMap()
                    )
                }
        }
    }

    fun updateFavorites(match: MatchDomain) {
        if (match.isFavoriteToUser)
            updateFavoritesUseCase.invokeRemoveFromFavorites(match)
        else
            updateFavoritesUseCase.invokeAddToFavorites(match)
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