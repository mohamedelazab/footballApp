package com.example.footballapp.ui.fixtures.epoxy

import com.airbnb.epoxy.EpoxyModelClass
import com.example.footballapp.R
import com.example.footballapp.databinding.ViewEmptyBinding
import com.example.footballapp.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass
abstract class EmptyViewModel : ViewBindingEpoxyModelWithHolder<ViewEmptyBinding>() {

    override fun getDefaultLayout(): Int = R.layout.view_empty
    override fun ViewEmptyBinding.bind() {

    }

    override fun ViewEmptyBinding.unbind() {

    }
}