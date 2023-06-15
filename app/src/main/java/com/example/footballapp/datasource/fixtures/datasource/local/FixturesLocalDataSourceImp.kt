package com.example.footballapp.datasource.fixtures.datasource.local

import android.content.SharedPreferences
import com.example.footballapp.datasource.fixtures.datasource.di.PrefModule
import com.example.footballapp.datasource.fixtures.datasource.mapper.toJsonString
import com.example.footballapp.datasource.fixtures.datasource.mapper.toMatch
import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

const val matchesSetKey = "matches_set_key"

class FixturesLocalDataSourceImp @Inject constructor(
    @Named(PrefModule.appPref) private val sharedPreferences: SharedPreferences,
) : FixturesLocalDataSource {

    override fun getFavoriteMatches(): Single<List<Match?>> =
        Single.just(
            sharedPreferences.getStringSet(matchesSetKey, setOf())?.map {
                it.toMatch()
            }
        )

    override fun removeFromFavorites(match: Match) =
        Completable.fromAction {
            val oldData = sharedPreferences.getStringSet(matchesSetKey, setOf())
            val newData = LinkedHashSet<String>()
            oldData?.let { newData.addAll(it) }
            newData.removeIf {
                it.toMatch()?.id == match.id
            }
            sharedPreferences.edit()
                .putStringSet(matchesSetKey, newData)
                .apply()
        }

    override fun addToFavorites(match: Match) {
        val oldData = sharedPreferences.getStringSet(matchesSetKey, setOf())
        val newData = mutableSetOf<String>()
        newData.add(match.toJsonString())
        oldData?.let { newData.addAll(it) }
        sharedPreferences.edit()
            .putStringSet(matchesSetKey, newData)
            .apply()
    }
}