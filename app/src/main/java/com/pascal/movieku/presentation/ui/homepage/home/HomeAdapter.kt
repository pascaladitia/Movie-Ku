package com.pascal.movieku.presentation.ui.homepage.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import com.pascal.movieku.databinding.ItemCardMoviesBinding
import com.pascal.movieku.utils.Constant
import com.bumptech.glide.Glide

class HomeAdapter(private val onClick: (MovieResponse.Result) -> Unit) :
    ListAdapter<MovieResponse.Result, HomeAdapter.MoviesHolder>(
        Differ()
    ) {

    class MoviesHolder(
        private val binding: ItemCardMoviesBinding,
        private val onClick: (MovieResponse.Result) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun result(currentMovies: MovieResponse.Result) {
            binding.apply {
                tvTitle.text = currentMovies.title
                tvOverview.text = currentMovies.overview
                tvRate.text = currentMovies.voteAverage.toString()
                Glide.with(root)
                    .load("${Constant.BASE_IMAGE}${currentMovies.posterPath}")
                    .into(ivPoster)
                root.setOnClickListener {
                    onClick(currentMovies)
                }
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<MovieResponse.Result>() {
        override fun areItemsTheSame(
            oldItem: MovieResponse.Result,
            newItem: MovieResponse.Result
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieResponse.Result,
            newItem: MovieResponse.Result
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val binding =
            ItemCardMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.result(getItem(position))
    }
}