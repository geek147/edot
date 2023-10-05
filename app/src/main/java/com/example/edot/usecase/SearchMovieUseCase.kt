package com.example.edot.usecase

import com.example.edot.model.Movies.Movie
import com.example.edot.repository.MovieRepository
import javax.inject.Inject
import com.example.edot.utils.Result

class SearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseCaseWrapper<List<Movie>, SearchMovieUseCase.Params>() {

    override suspend fun build(params: Params?): Result<List<Movie>> {
        if (params == null) throw IllegalArgumentException("Params should not be null")
        return repository.searchMovies(params.s, params.page, )
    }

    class Params(val s: String, val page: Int)
}