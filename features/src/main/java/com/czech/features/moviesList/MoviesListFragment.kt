package com.czech.features.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.czech.features.databinding.MoviesListFragmentBinding
import com.czech.features.moviesList.adapter.MoviesListAdapter
import com.czech.features.moviesList.adapter.MoviesListDiffCallback
import com.czech.features.utils.*
import kotlinx.coroutines.launch


class MoviesListFragment : Fragment() {

    private lateinit var binding: MoviesListFragmentBinding

    private val viewModel by activityViewModels<MoviesListViewModel>()

    private val moviesListAdapter by lazy { MoviesListAdapter(MoviesListDiffCallback) }

    private var networkValue = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MoviesListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesListRecycler.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = moviesListAdapter
        }

        checkInternetConnectivity()

        if (networkValue) {
            viewModel.getMoviesFromNetwork()
        } else {
            viewModel.getMoviesFromDB()
        }
        observe()
        onRefresh()
        navigateToDetailsPage()
    }

    private fun onRefresh() {
        binding.refresh.setOnRefreshListener{
            checkInternetConnectivity()

            if (networkValue) {
                viewModel.getMoviesFromNetwork()
            } else {
                viewModel.getMoviesFromDB()
            }
            binding.refresh.isRefreshing = false
        }

    }

    private fun checkInternetConnectivity() {
        viewModel.isNetworkConnected.observe(viewLifecycleOwner) { isConnected ->
            networkValue = when (isConnected) {
                false -> {
                    false
                }
                true -> {
                    true
                }
            }
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.moviesListState.collect {
                when (it) {
                    is MoviesListState.Loading -> {
                        binding.apply {
                            progressBar.show()
                            moviesListRecycler.hide()
                        }
                    }
                    is MoviesListState.Success -> {
                        binding.apply {
                            progressBar.hide()
                            moviesListRecycler.show()
                        }
                        if (it.data != null) {
                            moviesListAdapter.submitList(it.data)
                        }
                    }
                    is MoviesListState.Error -> {
                        binding.apply {
                            progressBar.hide()
                            moviesListRecycler.show()
                        }
                        requireActivity().showShortToast(it.message)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun navigateToDetailsPage() {
        moviesListAdapter.onClickItemListener = {
            if (networkValue) {
                launchFragment(
                    MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment2(
                        it.id!!
                    )
                )
            } else {
                requireActivity().showShortToast("You don't have any saved movies. Connect to the internet and try again")
            }
        }
    }

}