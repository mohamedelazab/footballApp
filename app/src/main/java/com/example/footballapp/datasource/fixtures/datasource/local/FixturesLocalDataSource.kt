package com.example.footballapp.datasource.fixtures.datasource.local

import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Single

interface FixturesLocalDataSource {

    fun getFavoriteMatches(): Single<List<Match?>>

    fun removeFromFavorites(match: Match)

    fun addToFavorites(match: Match)

}