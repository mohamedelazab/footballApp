package com.example.footballapp.datasource.fixtures.service

import com.example.footballapp.model.FixturesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("competitions/2021/matches")
    fun getFixtures(): Single<FixturesResponse>
}