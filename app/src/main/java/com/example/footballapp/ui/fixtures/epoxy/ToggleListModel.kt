package com.example.footballapp.ui.fixtures.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.footballapp.R
import com.example.footballapp.databinding.ViewToggleListBinding
import com.example.footballapp.helper.ViewBindingEpoxyModelWithHolder


@EpoxyModelClass
abstract class ToggleListModel : ViewBindingEpoxyModelWithHolder<ViewToggleListBinding>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onToggleItemChecked: (value: CheckedEnum) -> Unit

    override fun ViewToggleListBinding.bind() {
        group.setOnCheckedStateChangeListener { group, checkedId ->
            if (checkedId[0] == optionAll.id) {
                onToggleItemChecked(CheckedEnum.ALL)
            } else {
                onToggleItemChecked(CheckedEnum.FAVORITES)
            }
        }

    }

    override fun getDefaultLayout(): Int = R.layout.view_toggle_list
}

enum class CheckedEnum {
    ALL,
    FAVORITES
}