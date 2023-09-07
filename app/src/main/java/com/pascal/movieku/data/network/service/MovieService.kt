package com.pascal.movieku.data.network.service

import com.pascal.movieku.data.network.model.response.movie.MovieResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailMovieResponse
import com.pascal.movieku.utils.Constant
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.pascal.movieku.data.network.model.response.movie.details.DetailReviewsResponse
import com.pascal.movieku.data.network.model.response.movie.details.DetailVideosResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface MovieService {

    @GET(Constant.POPULAR)
    suspend fun getPopularMovie(): MovieResponse

    @GET(Constant.UP_COMING)
    suspend fun getUpComingMovie(): MovieResponse

    @GET(Constant.TOP_RATED)
    suspend fun getTopRatedMovie(): MovieResponse

    @GET(Constant.DETAIL_MOVIES)
    suspend fun getDetailMovie(@Path(Constant.ID_PATH) id: Int?): DetailMovieResponse

    @GET(Constant.VIDEOS)
    suspend fun getDetailVideos(@Path("id") id: Int): DetailVideosResponse

    @GET(Constant.REVIEWS)
    suspend fun getDetailReviews(@Path("id") id: Int): DetailReviewsResponse

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor : ChuckerInterceptor): MovieService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor {
                    val req = it.request()
                    val query = req.url
                        .newBuilder()
                        .addQueryParameter(Constant.API_NAME, Constant.API_KEY)
                        .build()
                    return@addInterceptor it.proceed(req.newBuilder().url(query).build())
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(MovieService::class.java)
        }
    }
}