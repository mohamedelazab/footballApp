package com.example.footballapp.datasource.fixtures.datasource.local

import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FixturesLocalDataSourceImp @Inject constructor(
) : FixturesLocalDataSource {

    override fun getFavoriteMatches(): Single<List<Match>> {
        TODO()
    }

    override fun addToFavorites(match: Match): Completable {
        TODO()
    }

    override fun removeFromFavorites(localId: Int): Completable {
        TODO()
    }
}