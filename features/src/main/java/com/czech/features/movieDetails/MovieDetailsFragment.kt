package com.czech.features.movieDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.czech.core.response.MovieDetails
import com.czech.core.utils.Constants
import com.czech.features.databinding.MovieDetailsFragmentBinding
import com.czech.features.utils.*
import kotlinx.coroutines.launch

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: MovieDetailsFragmentBinding

    private val viewModel by activityViewModels<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movieId

        viewModel.getMovieDetails(movieId)
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.moviesDetailState.collect {
                when (it) {
                    is MovieDetailState.Loading -> {
                        binding.apply {
                            progressBar.show()
                            movieDetailsFragmentContainer.hide()
                        }
                    }
                    is MovieDetailState.Success -> {
                        binding.apply {
                            progressBar.hide()
                            movieDetailsFragmentContainer.show()
                        }
                        if (it.data != null) {
                            displayDetails(it.data)
                        }
                    }
                    is MovieDetailState.Error -> {
                        binding.apply {
                            progressBar.hide()
                        }
                        requireActivity().showShortToast(it.message)
                    }
                    else -> {}
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun displayDetails(movie: MovieDetails) {
        binding.apply {

            Glide.with(requireActivity())
                .load("${Constants.BASE_IMAGE_PATH}${movie.backdropPath}")
                .into(backdrop)
            title.text = movie.title
            releaseYear.text = Converter.convertDateToYear(movie.releaseDate)
            val rating = movie.voteAverage?.div(2)
            if (rating != null) {
                ratingBar.rating = rating.toFloat()
            }
            ratingFraction.text = movie.voteAverage?.toFloat().toString() + "/10.0"
            langText.text = movie.originalLanguage
            overview.text = movie.overview
        }
    }

}