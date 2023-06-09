package com.example.footballapp.domain.usecase.fixtures

import com.example.footballapp.domain.repository.FixturesRepository
import com.example.footballapp.model.FixturesResponse
import io.reactivex.Single
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(private val repository: FixturesRepository) {

    operator fun invoke(): Single<FixturesResponse> = repository.getFixtures()
}