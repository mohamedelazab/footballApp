package com.example.footballapp.presentation.fixtures

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.footballapp.datasource.fixtures.LazySchedulers
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.usecase.fixtures.GetCurrentDayFixturesUseCase
import com.example.footballapp.domain.usecase.fixtures.GetFavoritesUseCase
import com.example.footballapp.domain.usecase.fixtures.GetFixturesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FixturesViewModel @AssistedInject constructor(
    @Assisted initialState: FixturesState,
    private val getFixturesUseCase: GetFixturesUseCase,
    private val getCurrentDayFixturesUseCase: GetCurrentDayFixturesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val lazySchedulers: LazySchedulers,
) : BaseMvRxViewModel<FixturesState>(initialState) {

    init {
        logStateChanges()
        getFavorites(true)
    }

    fun getFixtures(favorites: Map<String, List<MatchDomain>>) {
        getFixturesUseCase.invoke(favorites)
            .subscribeOn(lazySchedulers.io())
            .doFinally { getCurrentDayFixtures() }
            .execute { copy(fixturesState = it, allFixtures = (it)() ?: emptyMap()) }
    }

    fun getFavorites(isInit: Boolean = false) {
        setState { copy(allFixtures = emptyMap()) }
        getFavoritesUseCase.invokeAllFavorites()
            .subscribeOn(lazySchedulers.io())
            .doOnSuccess { if (isInit) getFixtures(it) }
            .execute { copy(favoritesState = it, allFixtures = (it)() ?: emptyMap()) }
    }

    fun getCurrentDayFixtures() {
        withState { state ->
            if (state.fixturesState !is Success) return@withState

            getCurrentDayFixturesUseCase.invoke((state.fixturesState)())
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
            getFavoritesUseCase.invokeRemoveFromFavorites(match)
        else
            getFavoritesUseCase.invokeAddToFavorites(match)
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