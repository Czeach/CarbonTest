package com.czech.features.moviesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czech.features.databinding.MoviesListFragmentBinding

class MoviesListFragment : Fragment() {

    private lateinit var binding: MoviesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MoviesListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

}