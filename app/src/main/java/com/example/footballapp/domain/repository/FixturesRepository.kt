package com.example.footballapp.domain.repository

import com.example.footballapp.model.FixturesResponse
import io.reactivex.Single

interface FixturesRepository {

    fun getFixtures(): Single<FixturesResponse>
}