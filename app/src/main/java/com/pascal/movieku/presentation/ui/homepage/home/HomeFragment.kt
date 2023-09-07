package com.pascal.movieku.presentation.ui.homepage.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import com.pascal.movieku.databinding.FragmentHomeBinding
import com.pascal.movieku.utils.Constant
import com.pascal.movieku.utils.validation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModelInstance.apply {
            getUser()
            getMovieListPopular()
            getMovieListUpComing()
            getMovieListTopRated()
        }
    }

    private fun onClick() {
        binding.apply {
            ivFavorite.setOnClickListener {
                moveNav(R.id.action_homeFragment_to_favoriteFragment)
            }

            ivProfile.setOnClickListener {
                moveNav(R.id.action_homeFragment_to_profileUserFragment)
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        binding.pbMovies.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        binding.clDisplay.isVisible = isVisible
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        lifecycleScope.apply {
            with(binding) {
                launchWhenStarted {
                    viewModelInstance.getUserResult.collect {
                        if (it is Resource.Success) {
                            tvShowName.text = "Hi, ${it.data?.name}"
                        }
                    }
                }

                launchWhenStarted {
                    viewModelInstance.getPopularResult.collect {
                        when (it) {
                            is Resource.Empty -> { }
                            is Resource.Loading -> {
                                showLoading(true)
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                setDataPopularAdapter(it.data?.results)
                            }
                            is Resource.Error -> { }
                        }
                    }
                }
                launchWhenStarted {
                    viewModelInstance.getUpComingResult.collect {
                        when (it) {
                            is Resource.Empty -> { }
                            is Resource.Loading -> {
                                showLoading(true)
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                setDataUpComingAdapter(it.data?.results)
                            }
                            is Resource.Error -> { }
                        }
                    }
                }

                launchWhenStarted {
                    viewModelInstance.getTopRatedResult.collect {
                        when (it) {
                            is Resource.Empty -> { }
                            is Resource.Loading -> {
                                showLoading(true)
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                setDataTopRatedAdapter(it.data?.results)
                            }
                            is Resource.Error -> { }
                        }
                    }
                }
            }
        }
    }

    private fun setDataPopularAdapter(results: List<MovieResponse.Result?>?) {
        binding.apply {
            val adapter = HomeAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment()
                it.id?.let { data -> parcel.id = data }
                moveNav(parcel)
            }
            adapter.submitList(results)
            rvMoviePopular.adapter = adapter
        }
    }

    private fun setDataUpComingAdapter(results: List<MovieResponse.Result?>?) {
        binding.apply {
            val adapter = HomeAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment()
                it.id?.let { data -> parcel.id = data }
                moveNav(parcel)
            }
            adapter.submitList(results)
            rvMovieUpComing.adapter = adapter
        }
    }

    private fun setDataTopRatedAdapter(results: List<MovieResponse.Result?>?) {
        binding.apply {
            val adapter = HomeAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment()
                it.id?.let { data -> parcel.id = data }
                moveNav(parcel)
            }
            adapter.submitList(results)
            rvMovieTopRated.adapter = adapter

            val firstList = results?.firstOrNull()
            tvTitleMovie.text = validation(firstList?.title.toString())
            tvRate.text = firstList?.voteAverage.toString()
            Glide.with(root)
                .load("${Constant.BASE_IMAGE}${firstList?.posterPath}")
                .into(ivMovie)

            ivMovie.setOnClickListener {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment()
                firstList?.id?.let { data -> parcel.id = data }
                moveNav(parcel)
            }
        }
    }
}