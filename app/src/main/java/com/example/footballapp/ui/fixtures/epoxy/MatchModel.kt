package com.example.footballapp.ui.fixtures.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.footballapp.R
import com.example.footballapp.databinding.ViewMatchBinding
import com.example.footballapp.domain.models.MatchDomain
import com.example.footballapp.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass
abstract class MatchModel : ViewBindingEpoxyModelWithHolder<ViewMatchBinding>() {

    @EpoxyAttribute
    lateinit var match: MatchDomain

    override fun ViewMatchBinding.bind() {
        tvHomeTeamName.text = match.homeTeam?.name
        tvAwayTeamName.text = match.awayTeam?.name
        if (match.status == MatchDomain.Status.FINISHES.value) {
            tvMatchResult.text = tvMatchResult.context.getString(
                R.string.text_result,
                match.score?.fullTime?.homeTeam.toString(),
                match.score?.fullTime?.awayTeam.toString()
            )
        } else {
            //TODO: you can use date util and move the whole condition outside the model
            tvMatchResult.text = match.utcDate.slice(11 until match.utcDate.length - 4)
        }
    }

    override fun getDefaultLayout(): Int = R.layout.view_match
}