package com.example.footballapp.datasource.fixtures.datasource

import com.example.footballapp.model.FixturesResponse
import io.reactivex.Single

interface FixturesRemoteDataSource {

    fun getFixtures(): Single<FixturesResponse>
}