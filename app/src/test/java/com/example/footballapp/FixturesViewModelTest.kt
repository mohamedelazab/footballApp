package com.example.footballapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MavericksTestRule
import com.airbnb.mvrx.withState
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.domain.usecase.fixtures.GetCurrentDayFixturesUseCase
import com.example.footballapp.domain.usecase.fixtures.GetFavoritesUseCase
import com.example.footballapp.domain.usecase.fixtures.GetFixturesUseCase
import com.example.footballapp.presentation.fixtures.FixturesState
import com.example.footballapp.presentation.fixtures.FixturesViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert
import org.junit.ClassRule
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class FixturesViewModelTest {
    private val lazyTestSchedulers = LazyTestSchedulers()
    private val getFixturesUseCase: GetFixturesUseCase = mockk(relaxed = true)
    private val getCurrentDayFixturesUseCase: GetCurrentDayFixturesUseCase = mockk(relaxed = true)
    private val getFavoritesUseCase: GetFavoritesUseCase = mockk(relaxed = true)
    private lateinit var fixturesViewModel: FixturesViewModel
    private var fixturesState = FixturesState()

    @Test
    fun `GIVEN initial state of viewModel WHEN getFixtures is invoked THEN fixturesState is Success`() {
        every { getFixturesUseCase.invoke() } returns Single.just(mapOf())
        initViewModel(fixturesState)
        fixturesViewModel.getFixtures(mapOf())
        lazyTestSchedulers.triggerActions()
        withState(fixturesViewModel) {
            Assert.assertTrue(it.fixturesState is Success)
        }
    }

    @Test
    fun `GIVEN initial state of viewModel WHEN getFixtures is invoked and sucess THEN invoke getCurrentDayFixturesUseCase`() {
        every { getFixturesUseCase.invoke() } returns Single.just(mapOf())
        initViewModel(fixturesState)
        fixturesViewModel.getFixtures(mapOf())
        lazyTestSchedulers.triggerActions()
        verify {
            getCurrentDayFixturesUseCase.invoke(mapOf())
        }
    }

    @Test
    fun `GIVEN initial state of viewModel WHEN getFixtures is invoked but failed THEN invoke getCurrentDayFixturesUseCase`() {
        every { getFixturesUseCase.invoke() } returns Single.error(Throwable())
        initViewModel(fixturesState)
        fixturesViewModel.getCurrentDayFixtures()
        lazyTestSchedulers.triggerActions()
        verify(exactly = 0) {
            getCurrentDayFixturesUseCase.invoke(mapOf())
        }
    }

    @Test
    fun `GIVEN initial state of viewModel WHEN getCurrentDayFixturesUseCase is invoked THEN currentDayFixtures is Success`() {
        val currentDay =
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val initialMap: MutableMap<String, List<MatchDomain>> = HashMap()
        initialMap["2023-02-29"] = listOf()
        initialMap["2023-04-31"] = listOf()
        initialMap[currentDay] = listOf()
        val expectedMap: MutableMap<String, List<MatchDomain>> = HashMap()
        expectedMap[currentDay] = listOf()
        every { getFixturesUseCase.invoke() } returns Single.just(initialMap)
        every { getFavoritesUseCase.invokeAllFavorites() } returns Single.just(emptyMap())
        every { getCurrentDayFixturesUseCase.invoke(initialMap) } returns Observable.just(
            expectedMap
        )
        initViewModel(fixturesState)
        fixturesViewModel.getCurrentDayFixtures()
        lazyTestSchedulers.triggerActions()
        withState(fixturesViewModel) {
            Assert.assertTrue(it.currentDayFixturesState is Success)
        }
    }

    @Test
    fun `GIVEN initial state of viewModel WHEN getCurrentDayFixturesUseCase is invoked but no matches today THEN currentDayFixtures is Fail`() {
        val initialMap: MutableMap<String, List<MatchDomain>> = HashMap()
        initialMap["2023-02-29"] = listOf()
        initialMap["2023-04-31"] = listOf()
        val expectedMap: MutableMap<String, List<MatchDomain>> = HashMap()
        every { getFixturesUseCase.invoke() } returns Single.just(initialMap)
        every { getFavoritesUseCase.invokeAllFavorites() } returns Single.just(emptyMap())
        every { getCurrentDayFixturesUseCase.invoke(initialMap) } returns Observable.just(
            expectedMap
        )
        initViewModel(fixturesState)
        fixturesViewModel.getCurrentDayFixtures()
        lazyTestSchedulers.triggerActions()
        withState(fixturesViewModel) {
            Assert.assertTrue((it.currentDayFixturesState)()!!.isEmpty())
        }
    }

    private fun initViewModel(viewModelState: FixturesState) {
        fixturesViewModel = FixturesViewModel(
            initialState = viewModelState,
            getFixturesUseCase = getFixturesUseCase,
            getCurrentDayFixturesUseCase = getCurrentDayFixturesUseCase,
            getFavoritesUseCase = getFavoritesUseCase,
            lazySchedulers = lazyTestSchedulers,
        ).also {
            fixturesViewModel = it
            fixturesState = viewModelState
        }
    }

    companion object {
        @JvmField
        @ClassRule
        val mvrxTestRule = MavericksTestRule()

        @JvmField
        @ClassRule
        val schedulers = InstantTaskExecutorRule()
    }
}