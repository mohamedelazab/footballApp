package com.example.footballapp.ui.favorite

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FavoritesViewModel @AssistedInject constructor(@Assisted initialState: FavoritesState) :
    BaseMvRxViewModel<FavoritesState>(initialState) {

    companion object :
        MavericksViewModelFactory<FavoritesViewModel, FavoritesState> by hiltMavericksViewModelFactory()

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<FavoritesViewModel, FavoritesState> {
        override fun create(
            state: FavoritesState,
        ): FavoritesViewModel
    }
}