package com.example.footballapp.datasource.fixtures.datasource.local

import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import io.reactivex.Single
import javax.inject.Inject

class FixturesLocalDataSourceImp @Inject constructor() : FixturesLocalDataSource {
    override fun getFixtures(): Single<FixturesResponse> = Single.just(FixturesResponse())
}