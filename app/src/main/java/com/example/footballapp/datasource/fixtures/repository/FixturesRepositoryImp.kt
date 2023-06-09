package com.example.footballapp.datasource.fixtures.repository

import com.example.footballapp.datasource.fixtures.datasource.FixturesRemoteDataSource
import com.example.footballapp.domain.repository.FixturesRepository
import com.example.footballapp.model.FixturesResponse
import io.reactivex.Single
import javax.inject.Inject

class FixturesRepositoryImp @Inject constructor(
    private val remoteDataSource: FixturesRemoteDataSource
) : FixturesRepository {

    override fun getFixtures(): Single<FixturesResponse> = remoteDataSource.getFixtures()
}