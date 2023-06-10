package com.example.footballapp

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.withState

abstract class BaseMvRxRecyclerFragment : Fragment(), MavericksView {
    protected lateinit var recyclerView: EpoxyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_epoxy_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = (view.findViewById(R.id.recycler_view) as? EpoxyRecyclerView)
            ?: throw Resources.NotFoundException(
                "layout must contains 'com.airbnb.epoxy.EpoxyRecyclerView' with id recycler_view ",
            )
        recyclerView.setController(epoxyController)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }

    /**
     * Provide the EpoxyController to use when building models for this Fragment.
     * Basic usages can simply use [simpleController]
     */
    abstract val epoxyController: MvRxEpoxyController

    fun <S : MvRxState, A : BaseMvRxViewModel<S>> BaseMvRxRecyclerFragment.simpleController(
        viewModel: A,
        buildModels: EpoxyController.(state: S) -> Unit,
    ) = MvRxEpoxyController {
        if (view == null || isRemoving) return@MvRxEpoxyController
        withState(viewModel) { state ->
            buildModels(state)
        }
    }

    fun <S1 : MvRxState, A : BaseMvRxViewModel<S1>, S2 : MvRxState, B : BaseMvRxViewModel<S2>> BaseMvRxRecyclerFragment.simpleController(
        viewModel1: A,
        viewModel2: B,
        buildModels: EpoxyController.(state1: S1, state2: S2) -> Unit,
    ) = MvRxEpoxyController {
        if (view == null || isRemoving) return@MvRxEpoxyController
        withState(viewModel1, viewModel2) { state1, state2 ->
            buildModels(state1, state2)
        }
    }

    open class MvRxEpoxyController(
        val buildModelsCallback: EpoxyController.() -> Unit = {},
    ) : AsyncEpoxyController() {

        fun update() {
            requestModelBuild()
        }

        override fun buildModels() {
            buildModelsCallback()
        }
    }
}
