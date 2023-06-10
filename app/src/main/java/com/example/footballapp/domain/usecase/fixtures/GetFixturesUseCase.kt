package com.example.footballapp.domain.usecase.fixtures

import com.example.footballapp.domain.repository.FixturesRepository
import com.example.footballapp.model.Match
import io.reactivex.Single
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(private val repository: FixturesRepository) {

    operator fun invoke(): Single<Map<String, List<Match>>> =
        repository.getFixtures()
            .filter { it.matches.isNotEmpty() }
            .map {
                it.matches.sortedByDescending { it.utcDate }
                    .groupBy { match ->
                        match.utcDate.slice(0..9)
                    }
            }.toSingle()
}