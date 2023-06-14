package com.example.footballapp.datasource.fixtures.datasource.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PrefModule {

    private val appPrefName = "appPref"
    private val backedUpAppPreferencesPrefName = "app-prefs"

    @Provides
    @Singleton
    @Named(appPref)
    fun providesAppPref(@ApplicationContext context: Context) =
        context.getSharedPreferences(appPrefName, Context.MODE_PRIVATE)!!

    @Provides
    @Singleton
    @Named(BackedUpAppPreferencesPref)
    fun providesBackedUpAppPreferencesPref(@ApplicationContext context: Context) =
        context.getSharedPreferences(backedUpAppPreferencesPrefName, Context.MODE_PRIVATE)!!

    const val appPref = "appPref"
    const val BackedUpAppPreferencesPref = "BackedUpAppPreferencesPref"
}