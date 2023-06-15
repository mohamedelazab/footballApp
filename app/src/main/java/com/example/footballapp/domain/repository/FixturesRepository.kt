package com.example.footballapp.domain.repository

import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Completable
import io.reactivex.Single

interface FixturesRepository {

    fun getFixtures(): Single<FixturesResponse>

    fun getFavoriteMatches(): Single<List<Match?>>

    fun removeFromFavorites(match: Match): Completable

    fun addToFavorites(match: Match)
}