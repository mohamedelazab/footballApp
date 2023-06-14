package com.example.footballapp.datasource.fixtures.datasource.remote

import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import io.reactivex.Single

interface FixturesRemoteDataSource {
    fun getFixtures(): Single<FixturesResponse>
}