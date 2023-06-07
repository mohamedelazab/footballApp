package com.example.footballapp.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.example.footballapp.ui.favorite.FavoritesViewModel
import com.example.footballapp.ui.fixtures.FixturesViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(FixturesViewModel::class)
    fun provideFixturesViewModel(factory: FixturesViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun provideFavoritesViewModel(factory: FavoritesViewModel.Factory): AssistedViewModelFactory<*, *>
}