package com.example.edot.datasource.remote

import com.example.edot.model.Movies
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService {

    @GET(".")
    suspend fun searchMovies(
        @Query("s") s: String,
        @Query("r") r: String,
        @Query("page") page: Int
    ): Response<Movies>

    companion object {
        operator fun invoke(retrofit: Retrofit): MovieApiService = retrofit.create(MovieApiService::class.java)
    }
}
