package com.pascal.movieku.presentation.ui.homepage.detailmovie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.network.model.response.movie.details.DetailMovieResponse
import com.pascal.movieku.databinding.FragmentDetailMovieBinding
import com.pascal.movieku.utils.Constant
import com.bumptech.glide.Glide
import com.pascal.movieku.data.network.model.response.movie.details.DetailReviewsResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailVideosResponse
import com.pascal.movieku.data.network.model.response.movie.details.Reviews
import com.pascal.movieku.utils.Constant.BASE_YT_WATCH_URL
import com.pascal.movieku.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailMovieViewModel>(
    FragmentDetailMovieBinding::inflate
) {
    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModelInstance.apply {
            getMovieDetail(args.id)
            getVideosDetail(args.id)
            getReviewsDetail(args.id)
            favMovie()
        }
    }

    private fun onClick() {
        binding.apply {
            ivBack.setOnClickListener {
                moveNav()
            }
            ibFavorite.setOnClickListener {
                viewModelInstance.stateFavorite(args.id)
            }
        }
    }

    private fun favMovie() {
        viewModelInstance.getFavoriteById(args.id)
    }

    override fun showLoading(isVisible: Boolean) {
        binding.pbDetailMovies.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        binding.clDisplay.isVisible = isVisible
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModelInstance.getMovieDetailResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            setContentData(it.data)
                        }
                        is Resource.Error -> {}
                    }
                }
            }
            launchWhenStarted {
                viewModelInstance.getVideosDetailResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            setContentVideos(it.data)
                        }
                        is Resource.Error -> {}
                    }
                }
            }
            launchWhenStarted {
                viewModelInstance.getReviewsDetailResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            setContentReviews(it.data)
                        }
                        is Resource.Error -> {}
                    }
                }
            }
            launchWhenStarted {
                viewModelInstance.getFavoriteByIdResult.collect {
                    if (it is Resource.Success) {
                        stateFavorite(it.data)
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.insertFavoriteResult.collect {
                    if (it is Resource.Success) {
                        showMessageSnackBar(true, it.message)
                        favMovie()
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.deleteFavoriteByIdResult.collect {
                    if (it is Resource.Success) {
                        showMessageSnackBar(true, it.message)
                        favMovie()
                    }
                }
            }
        }
    }

    private fun stateFavorite(data: Boolean?) {
        when (data) {
            true -> setStateFav(R.drawable.ic_favorite_saved)
            else -> setStateFav(R.drawable.ic_favorite_not_saved)
        }
    }

    private fun setStateFav(icSelectorFav: Int) {
        binding.apply {
            ibFavorite.setImageResource(icSelectorFav)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setContentData(data: DetailMovieResponse?) {
        binding.apply {
            data?.let {
                val listGenre = it.genres?.joinToString { data -> data?.name.toString() }
                Glide.with(root)
                    .load("${Constant.BASE_IMAGE}${it.posterPath}")
                    .into(ivPoster)
                tvStatus.text = data.status
                tvTitle.text = data.title
                tvGenre.text = "Genre: $listGenre"
                tvRating.text = "Rating: ${data.voteAverage}"
                tvReleaseDate.text = "Release Date: ${data.releaseDate}"
                tvOverview.text = data.overview
            }
        }
    }

    private fun setContentVideos(data: DetailVideosResponse?) {
        if (data?.result != null) {
            val adapter = VideosAdapter(data.result, object : VideosAdapter.OnClickListener {
                override fun detail(item: DetailVideosResponse.Videos) {
                    startVideos(item.key)
                }
            })
            binding.rvVideos.adapter = adapter
        }
    }

    private fun startVideos(key: String) {
        val url = "$BASE_YT_WATCH_URL$key"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        requireContext().startActivity(intent)
    }

    private fun setContentReviews(data: DetailReviewsResponse?) {
        if (data?.results != null) {
            val adapter = ReviewsAdapter(data.results, object : ReviewsAdapter.OnClickListener {
                override fun detail(item: Reviews) {}
            })
            binding.rvReviews.adapter = adapter
        }
    }

}