package com.example.footballapp.domain.usecase.fixtures

import com.example.footballapp.datasource.fixtures.datasource.mapper.toMatch
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.repository.FixturesRepository
import javax.inject.Inject

class UpdateFavoritesUseCase @Inject constructor(private val repository: FixturesRepository) {

    fun invokeRemoveFromFavorites(match: MatchDomain) =
        repository.removeFromFavorites(match.toMatch())

    fun invokeAddToFavorites(match: MatchDomain) =
        repository.addToFavorites(match.toMatch())
}