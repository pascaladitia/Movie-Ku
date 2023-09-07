package com.pascal.movieku.data.network.datasource

import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailMovieResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailReviewsResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailVideosResponse
import com.pascal.movieku.data.network.service.MovieService
import javax.inject.Inject

interface MovieDataSource {
    suspend fun getMoviesPopular(): MovieResponse
    suspend fun getMoviesUpComing(): MovieResponse
    suspend fun getMoviesTopRated(): MovieResponse
    suspend fun getMoviesDetail(movieId: Int): DetailMovieResponse
    suspend fun getVideosDetail(movieId: Int): DetailVideosResponse
    suspend fun getReviewsDetail(movieId: Int): DetailReviewsResponse
}

class MovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService
): MovieDataSource {

    override suspend fun getMoviesPopular(): MovieResponse {
        return movieService.getPopularMovie()
    }

    override suspend fun getMoviesUpComing(): MovieResponse {
        return movieService.getUpComingMovie()
    }

    override suspend fun getMoviesTopRated(): MovieResponse {
        return movieService.getTopRatedMovie()
    }

    override suspend fun getMoviesDetail(movieId: Int): DetailMovieResponse {
        return movieService.getDetailMovie(movieId)
    }

    override suspend fun getVideosDetail(movieId: Int): DetailVideosResponse {
        return movieService.getDetailVideos(movieId)
    }

    override suspend fun getReviewsDetail(movieId: Int): DetailReviewsResponse {
        return movieService.getDetailReviews(movieId)
    }
}