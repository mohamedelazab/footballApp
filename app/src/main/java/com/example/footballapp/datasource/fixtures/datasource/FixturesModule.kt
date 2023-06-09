package com.example.footballapp.datasource.fixtures.datasource

import com.example.footballapp.datasource.fixtures.repository.FixturesRepositoryImp
import com.example.footballapp.datasource.fixtures.service.ApiService
import com.example.footballapp.domain.repository.FixturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FixturesModule {
    @Provides
    @Singleton
    fun provideFixturesRepository(remoteDataSource: FixturesRemoteDataSource): FixturesRepository =
        FixturesRepositoryImp(remoteDataSource)

    @Provides
    @Singleton
    fun provideFixturesRemoteDataSource(apiService: ApiService): FixturesRemoteDataSource =
        FixturesRemoteDataSourceImp(apiService)
}
