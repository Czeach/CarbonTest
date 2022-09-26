package com.czech.features.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.czech.features.databinding.MovieDetailsFragmentBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: MovieDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


}