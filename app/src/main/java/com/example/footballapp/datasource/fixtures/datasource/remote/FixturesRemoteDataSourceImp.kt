package com.example.footballapp.datasource.fixtures.datasource.remote

import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import com.example.footballapp.datasource.fixtures.service.ApiService
import io.reactivex.Single
import javax.inject.Inject

class FixturesRemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
) : FixturesRemoteDataSource {
    override fun getFixtures(): Single<FixturesResponse> = apiService.getFixtures()
}