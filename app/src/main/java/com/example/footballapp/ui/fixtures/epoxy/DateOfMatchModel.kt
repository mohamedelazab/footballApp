package com.example.footballapp.ui.fixtures.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.footballapp.R
import com.example.footballapp.databinding.ViewDateOfMatchBinding
import com.example.footballapp.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass
abstract class DateOfMatchModel : ViewBindingEpoxyModelWithHolder<ViewDateOfMatchBinding>() {

    @EpoxyAttribute
    lateinit var dayDate: String

    override fun ViewDateOfMatchBinding.bind() {
        tvDateOfMatch.text = dayDate
    }

    override fun getDefaultLayout(): Int = R.layout.view_date_of_match
}