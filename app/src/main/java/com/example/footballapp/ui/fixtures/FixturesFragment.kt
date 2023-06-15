package com.example.footballapp.ui.fixtures

import android.util.Log
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
import com.example.footballapp.ui.fixtures.epoxy.emptyView
import com.example.footballapp.ui.fixtures.epoxy.match
import com.example.footballapp.ui.fixtures.epoxy.toggleList
import java.util.UUID

class FixturesFragment : BaseMvRxRecyclerFragmentWithViewBinding<FragmentFixturesBinding>() {

    private val viewModel: FixturesViewModel by fragmentViewModel()
    private lateinit var simpleController: MvRxEpoxyController

    override val epoxyController: MvRxEpoxyController
        get() {
            if (!::simpleController.isInitialized)
                simpleController = simpleController(viewModel) { state ->

                    toggleList {
                        id("toggle_list_View")
                        onAllFixturesChecked {
                            if (state.favoritesState is Success)
                                viewModel.getFixtures((state.favoritesState)())
                        }
                        onFavoritesChecked {
                            viewModel.getFavorites()
                        }
                    }
                    if (state.isFixturesSuccess || state.isFavoritesSuccess) {
                        state.allFixtures.forEach { (date, matches) ->
                            dateOfMatch {
                                id(date)
                                dayDate(date)
                            }
                            matches.forEach { match ->
                                Log.d("MATCHES_DOMAIN", match.isFavoriteToUser.toString())
                                match {
                                    id(match.id)
                                    match(match)
                                    onFavoriteClickListener {
                                        viewModel.updateFavorites(match)
                                    }
                                }
                            }
                        }
                        if (state.allFixtures.isEmpty() && state.isFixturesSuccess) {
                            emptyView {
                                id(UUID.randomUUID().hashCode())
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
                state.fixturesState is Loading || state.currentDayFixturesState is Loading
        }
    }
}