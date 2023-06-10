package com.example.footballapp.datasource.fixtures.datasource.local

import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import io.reactivex.Single

interface FixturesLocalDataSource {

    fun getFixtures(): Single<FixturesResponse>
}