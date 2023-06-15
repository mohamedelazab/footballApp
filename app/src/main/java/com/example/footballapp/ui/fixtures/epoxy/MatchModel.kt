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

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onFavoriteClickListener: () -> Unit

    override fun ViewMatchBinding.bind() {
        btnFavRestaurant.isSelected = match.isFavoriteToUser
        btnFavRestaurant.setOnClickListener {
            btnFavRestaurant.isSelected = !btnFavRestaurant.isSelected
            onFavoriteClickListener()
        }
        tvHomeTeamName.text = match.homeTeam?.name
        tvAwayTeamName.text = match.awayTeam?.name
        if (match.status == MatchDomain.Status.FINISHES.value) {
            tvMatchResult.text = tvMatchResult.context.getString(
                R.string.text_result,
                match.score?.fullTime?.homeTeam.toString(),
                match.score?.fullTime?.awayTeam.toString()
            )
        } else {
            tvMatchResult.text = match.utcDate.slice(11 until match.utcDate.length - 4)
        }
    }

    override fun ViewMatchBinding.unbind() {
        btnFavRestaurant.setOnClickListener(null)
    }

    override fun getDefaultLayout(): Int = R.layout.view_match
}