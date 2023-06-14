package com.example.footballapp.datasource.fixtures.datasource.local

import android.content.SharedPreferences
import com.example.footballapp.datasource.fixtures.datasource.di.PrefModule
import com.example.footballapp.datasource.fixtures.datasource.mapper.toJsonString
import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

const val matchesSharePrefKey = "matches_key"

class FixturesLocalDataSourceImp @Inject constructor(
    @Named(PrefModule.appPref) private val sharedPreferences: SharedPreferences,
) : FixturesLocalDataSource {

    override fun getFavoriteMatches(): Single<List<Match>> =
        return TODO("Provide the return value")

    override fun removeFromFavorites(match: Match): Completable =
        Completable.fromAction {
            sharedPreferences.edit().remove(match.id.toString()).apply()
        }

    override fun addToFavorites(match: Match): Completable =
        Completable.fromAction {
            sharedPreferences.edit()
                .putString(matchesSharePrefKey, match.toJsonString())
                .apply()
        }
}