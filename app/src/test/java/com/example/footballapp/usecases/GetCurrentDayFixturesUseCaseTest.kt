package com.example.footballapp.usecases

import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.usecase.fixtures.GetCurrentDayFixturesUseCase
import io.mockk.mockk
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class GetCurrentDayFixturesUseCaseTest {

    private val getCurrentDayFixturesUseCase: GetCurrentDayFixturesUseCase = mockk(relaxed = true)

    @Test
    fun `invoke()_when no matches in today or tomorrow then return nothing`() {
        val map: MutableMap<String, List<MatchDomain>> = HashMap()
        map["2023-02-29"] = listOf()
        map["2023-04-31"] = listOf()

        getCurrentDayFixturesUseCase.invoke(map)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue {
                it.isEmpty()
            }
    }

    @Test
    fun `invoke()_when has matches in today then return nothing`() {
        val currentDay =
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val map: MutableMap<String, List<MatchDomain>> = HashMap()
        map["2023-02-29"] = listOf()
        map["2023-04-31"] = listOf()
        map[currentDay] = listOf()
        getCurrentDayFixturesUseCase.invoke(map)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue {
                it[currentDay] == map[currentDay]
            }
    }

    @Test
    fun `invoke()_when has matches in next day then return nothing`() {
        val tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val map: MutableMap<String, List<MatchDomain>> = HashMap()
        map["2023-02-29"] = listOf()
        map["2023-04-31"] = listOf()
        map[tomorrow] = listOf()
        getCurrentDayFixturesUseCase.invoke(map)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue {
                it[tomorrow] == map[tomorrow]
            }
    }

    @Test
    fun `invoke()_when has matches in today and next day then return nothing`() {
        val currentDay =
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val map: MutableMap<String, List<MatchDomain>> = HashMap()
        map["2023-02-29"] = listOf()
        map[currentDay] = listOf()
        map[tomorrow] = listOf()
        getCurrentDayFixturesUseCase.invoke(map)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .test()
            .assertValue {
                it[currentDay] == map[currentDay]
            }
    }
}