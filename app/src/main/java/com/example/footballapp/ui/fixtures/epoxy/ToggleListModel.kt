package com.example.footballapp.ui.fixtures.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.footballapp.R
import com.example.footballapp.databinding.ViewToggleListBinding
import com.example.footballapp.helper.ViewBindingEpoxyModelWithHolder


@EpoxyModelClass
abstract class ToggleListModel : ViewBindingEpoxyModelWithHolder<ViewToggleListBinding>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onAllFixturesChecked: () -> Unit

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onFavoritesChecked: () -> Unit

    override fun ViewToggleListBinding.bind() {
        group.setOnCheckedStateChangeListener { group, checkedId ->
            if (checkedId.isEmpty()) {

            } else if (checkedId[0] == optionAll.id) {
                onAllFixturesChecked()
            } else {
                onFavoritesChecked()
            }
        }

    }

    override fun getDefaultLayout(): Int = R.layout.view_toggle_list
}