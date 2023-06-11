package com.example.footballapp.datasource.fixtures.repository

import com.example.footballapp.datasource.fixtures.datasource.local.FixturesLocalDataSource
import com.example.footballapp.datasource.fixtures.datasource.remote.FixturesRemoteDataSource
import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import com.example.footballapp.datasource.fixtures.models.Match
import com.example.footballapp.domain.repository.FixturesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FixturesRepositoryImp @Inject constructor(
    private val remoteDataSource: FixturesRemoteDataSource,
    private val localDataSource: FixturesLocalDataSource
) : FixturesRepository {

    override fun getFixtures(): Single<FixturesResponse> =
        remoteDataSource.getFixtures()

    override fun getFavoriteMatches(): Single<List<Match>> =
        localDataSource.getFavoriteMatches()

    override fun addToFavorites(match: Match): Completable =
        localDataSource.addToFavorites(match)

    override fun removeFromFavorites(localId: Int): Completable =
        localDataSource.removeFromFavorites(localId)
}