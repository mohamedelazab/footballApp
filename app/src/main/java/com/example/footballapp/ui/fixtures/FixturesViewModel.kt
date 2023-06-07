package com.example.footballapp.ui.fixtures

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FixturesViewModel @AssistedInject constructor(@Assisted initialState: FixturesState) :
    BaseMvRxViewModel<FixturesState>(initialState) {

    companion object :
        MavericksViewModelFactory<FixturesViewModel, FixturesState> by hiltMavericksViewModelFactory()

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<FixturesViewModel, FixturesState> {
        override fun create(
            state: FixturesState,
        ): FixturesViewModel
    }
}