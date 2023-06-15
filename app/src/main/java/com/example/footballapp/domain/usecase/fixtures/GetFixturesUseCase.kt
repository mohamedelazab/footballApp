package com.example.footballapp.domain.usecase.fixtures

import com.example.footballapp.datasource.fixtures.datasource.mapper.toDomainMatch
import com.example.footballapp.datasource.fixtures.models.Match
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.repository.FixturesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(private val repository: FixturesRepository) {

    operator fun invoke(savedMatches: Map<String, List<MatchDomain>> = emptyMap()): Single<Map<String, List<MatchDomain>>> =
        repository.getFixtures()
            .filter { it.matches.isNotEmpty() }
            .map {
                it.matches.sortedByDescending { it.utcDate }
                    .map { match ->
                        match.toDomainMatch(isFavourite = isFavorite(savedMatches, match))
                    }
                    .groupBy { match ->
                        match.utcDate.slice(0..9)
                    }
            }.toSingle()

    private fun isFavorite(savedMatches: Map<String, List<MatchDomain>>, match: Match): Boolean {
        savedMatches.forEach {
            it.value.forEach {
                if (it.id == match.id) return true
            }
        }
        return false
    }
}