package com.example.footballapp.ui.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.footballapp.BaseMvRxRecyclerFragmentWithViewBinding
import com.example.footballapp.databinding.FragmentFixturesBinding
import com.example.footballapp.presentation.fixtures.FixturesViewModel
import com.example.footballapp.ui.fixtures.epoxy.dateOfMatch
import com.example.footballapp.ui.fixtures.epoxy.match

class FixturesFragment : BaseMvRxRecyclerFragmentWithViewBinding<FragmentFixturesBinding>() {

    private val viewModel: FixturesViewModel by fragmentViewModel()
    private lateinit var simpleController: MvRxEpoxyController

    override val epoxyController: MvRxEpoxyController
        get() {
            if (!::simpleController.isInitialized)
                simpleController = simpleController(viewModel) { state ->

                    if (state.currentDayFixtures is Success) {
                        (state.currentDayFixtures)().firstNotNullOfOrNull { (date, matches) ->
                            dateOfMatch {
                                id(date)
                                dayDate(date)
                            }
                            matches.forEach {
                                match {
                                    id(it.id)
                                    match(it)
                                    onFavoriteClickListener {

                                    }
                                }
                            }
                        }
                    }
                    if (state.fixturesState is Success) {
                        (state.fixturesState)().forEach { (date, matches) ->
                            dateOfMatch {
                                id(date)
                                dayDate(date)
                            }
                            matches.forEach {
                                match {
                                    id(it.id)
                                    match(it)
                                    onFavoriteClickListener {

                                    }
                                }
                            }
                        }
                    }

                }.apply { setFilterDuplicates(true) } // https://github.com/airbnb/epoxy/wiki/Epoxy-Controller#filtering-duplicates

            return simpleController
        }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFixturesBinding
        get() = FragmentFixturesBinding::inflate

    override fun invalidate() {
        recyclerView.requestModelBuild()
        withState(viewModel) { state ->
            binding.progressBar.isVisible =
                state.fixturesState is Loading || state.currentDayFixtures is Loading
        }
    }
}