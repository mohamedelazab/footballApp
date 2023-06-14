package com.example.footballapp.datasource.fixtures.datasource

import android.content.SharedPreferences
import com.example.footballapp.datasource.fixtures.datasource.di.PrefModule
import com.example.footballapp.datasource.fixtures.datasource.local.FixturesLocalDataSource
import com.example.footballapp.datasource.fixtures.datasource.local.FixturesLocalDataSourceImp
import com.example.footballapp.datasource.fixtures.datasource.remote.FixturesRemoteDataSource
import com.example.footballapp.datasource.fixtures.datasource.remote.FixturesRemoteDataSourceImp
import com.example.footballapp.datasource.fixtures.repository.FixturesRepositoryImp
import com.example.footballapp.datasource.fixtures.service.ApiService
import com.example.footballapp.domain.repository.FixturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FixturesModule {
    @Provides
    @Singleton
    fun provideFixturesRepository(
        remoteDataSource: FixturesRemoteDataSource,
        localDataSource: FixturesLocalDataSource
    ): FixturesRepository =
        FixturesRepositoryImp(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideFixturesRemoteDataSource(apiService: ApiService): FixturesRemoteDataSource =
        FixturesRemoteDataSourceImp(apiService)

    @Provides
    @Singleton
    fun provideFixturesLocalDataSource(
        @Named(PrefModule.appPref) sharedPreferences: SharedPreferences,
    ): FixturesLocalDataSource =
        FixturesLocalDataSourceImp(sharedPreferences)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)
}
