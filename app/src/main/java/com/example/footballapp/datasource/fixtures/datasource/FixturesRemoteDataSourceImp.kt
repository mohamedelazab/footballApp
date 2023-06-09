package com.example.footballapp.datasource.fixtures.datasource

import com.example.footballapp.datasource.fixtures.service.ApiService
import com.example.footballapp.model.FixturesResponse
import io.reactivex.Single
import javax.inject.Inject

// add explicit argument to all
class FixturesRemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
) : FixturesRemoteDataSource {
    override fun getFixtures(): Single<FixturesResponse> = apiService.getFixtures()
}