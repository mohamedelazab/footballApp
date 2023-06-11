package com.example.footballapp.datasource.fixtures.datasource.local

import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Completable
import io.reactivex.Single

interface FixturesLocalDataSource {

    fun getFavoriteMatches(): Single<List<Match>>

    fun addToFavorites(match: Match): Completable

    fun removeFromFavorites(localId: Int): Completable
}