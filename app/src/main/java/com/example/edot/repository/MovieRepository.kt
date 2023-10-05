package com.example.edot.repository

import com.example.edot.model.Movies.Movie
import com.example.edot.utils.Result

interface MovieRepository {

    suspend fun searchMovies(
        s: String,
        page: Int
    ): Result<List<Movie>>

}
