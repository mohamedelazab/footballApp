package com.example.footballapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.footballapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(), MavericksView {

    private val viewModel: FavoritesViewModel by fragmentViewModel()
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun invalidate() {
        withState(viewModel){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}