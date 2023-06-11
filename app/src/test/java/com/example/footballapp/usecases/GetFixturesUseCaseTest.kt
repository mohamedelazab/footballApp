package com.example.footballapp.usecases

import com.example.footballapp.datasource.fixtures.datasource.mapper.toDomainMatch
import com.example.footballapp.datasource.fixtures.models.FixturesResponse
import com.example.footballapp.datasource.fixtures.models.Match
import com.example.footballapp.domain.repository.FixturesRepository
import com.example.footballapp.domain.usecase.fixtures.GetFixturesUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class GetFixturesUseCaseTest {

    private val fixturesRepository: FixturesRepository = mockk(relaxed = false)
    private val getFixturesUseCase: GetFixturesUseCase = GetFixturesUseCase(fixturesRepository)

    @Test
    fun `GIVEN fixture response has no matches from repository WHEN invoke fun triggered of restaurants then return nothing`() {
        val remoteResponse = FixturesResponse(matches = listOf())
        every { fixturesRepository.getFixtures() } returns Single.just(remoteResponse)
        getFixturesUseCase.invoke()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertNoValues()
    }

    @Test
    fun `GIVEN fixture response returned from repository WHEN invoke fun triggered then return response after map to domain`() {
        val remoteResponse =
            FixturesResponse(matches = listOf(Match(utcDate = "2023-02-29T14:00:00Z")))
        every { fixturesRepository.getFixtures() } returns Single.just(remoteResponse)
        getFixturesUseCase.invoke()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue {
                it["2023-02-29"] == remoteResponse.matches.map { it.toDomainMatch() }
            }
    }

    @Test
    fun `GIVEN fixture response sorted ascending by date from repository WHEN invoke fun triggered then return response sorted descending`() {
        val remoteResponse = FixturesResponse(
            matches = listOf(
                Match(utcDate = "2022-08-17T14:00:00Z"),
                Match(utcDate = "2022-08-18T14:00:00Z"),
                Match(utcDate = "2022-08-19T14:00:00Z"),
                Match(utcDate = "2022-08-20T14:00:00Z"),
            )
        )
        every { fixturesRepository.getFixtures() } returns Single.just(remoteResponse)
        getFixturesUseCase.invoke()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue {
                it.keys.first() == "2022-08-20" && it.keys.last() == "2022-08-17"
            }
    }

    @Test
    fun `GIVEN fixture response WHEN invoke fun triggered then return response mapped to domain model successfully`() {
        val remoteResponse = FixturesResponse(
            matches = listOf(
                Match(utcDate = "2022-08-17T14:00:00Z"),
            )
        )
        every { fixturesRepository.getFixtures() } returns Single.just(remoteResponse)
        getFixturesUseCase.invoke()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue { map ->
                remoteResponse.matches.map { it.toDomainMatch() }[0].utcDate == map["2022-08-17"]?.get(
                    0
                )?.utcDate
            }
    }

    @Test
    fun `GIVEN fixture response WHEN invoke fun triggered then verify generating date as map key is done successfully`() {
        val remoteResponse = FixturesResponse(
            matches = listOf(
                Match(utcDate = "2022-08-17T14:00:00Z"),
            )
        )
        every { fixturesRepository.getFixtures() } returns Single.just(remoteResponse)
        getFixturesUseCase.invoke()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue { map ->
                map.keys.first() == "2022-08-17"
            }
    }
}