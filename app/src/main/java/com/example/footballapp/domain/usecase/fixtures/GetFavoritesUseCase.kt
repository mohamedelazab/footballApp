package com.example.footballapp.domain.usecase.fixtures

import com.example.footballapp.datasource.fixtures.datasource.mapper.toDomainMatch
import com.example.footballapp.datasource.fixtures.datasource.mapper.toMatch
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.repository.FixturesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repository: FixturesRepository) {

    fun invokeAllFavorites(): Single<Map<String, List<MatchDomain>>> =
        repository.getFavoriteMatches().map { list ->
            list.filterNotNull()
                .sortedByDescending { it.utcDate }
                .map { match ->
                    match.toDomainMatch(true)
                }.groupBy { match ->
                    match.utcDate.slice(0..9)
                }
        }

    fun invokeRemoveFromFavorites(match: MatchDomain) =
        repository.removeFromFavorites(match.toMatch())

    fun invokeAddToFavorites(match: MatchDomain) =
        repository.addToFavorites(match.toMatch())
}