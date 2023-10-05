package com.example.edot.repository

import android.util.Log
import com.example.edot.datasource.remote.MovieApiService
import com.example.edot.model.Movies.Movie
import javax.inject.Inject
import com.example.edot.utils.Result

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
) : MovieRepository {

    override suspend fun searchMovies(
        s: String,
        page: Int,
    ): Result<List<Movie>> {
        return try {
            val result = movieApiService.searchMovies(
                s = s,
                r = "json",
                page = page
            )
//            if (result.isSuccessful) {
//                val remoteData = result.body()
//                val items = remoteData?.search
//                return if (remoteData != null && !items.isNullOrEmpty()) {
//                    Result.Success(items)
//                } else {
//                    Result.Success(emptyList())
//                }
//            } else {
//                return Result.Error(Exception("Invalid data/failure"))
//            }
            return Result.Success(getMockData())
        } catch (e: Exception) {
            Log.e("ApiCalls", "Call error: ${e.localizedMessage}", e.cause)
            Result.Error(Exception(e.cause))
        }
    }
}

fun getMockData() : List<Movie> {
    val listMovie = listOf<Movie>(
        Movie(
            imdbID = "tt4154796",
            title = "Avengers: Endgame",
            year = "2019",
            type = "movie",
            poster = "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg"
        ),
        Movie(
            imdbID = "tt10025738",
            title = "Avengers: Endgame and the Latest Captain Marvel Outrage!!",
            year = "2019",
            type = "movie",
            poster = "https://m.media-amazon.com/images/M/MV5BZjg2ZTM3OTgtY2ExMS00OGM4LTg3NDEtNjQ0MjJiZDFmMGFkXkEyXkFqcGdeQXVyMDY3OTcyOQ@@._V1_SX300.jpg"
        ),
        Movie(
            imdbID = "tt10240638",
            title = "Marvel Studios' Avengers: Endgame LIVE Red Carpet World Premiere",
            year = "2019",
            type = "movie",
            poster = "https://m.media-amazon.com/images/M/MV5BNThjZDgwZTYtMjdmYy00ZmUyLTk4NTUtMzdjZmExODQ3ZmY4XkEyXkFqcGdeQXVyMjkzMDgyNTg@._V1_SX300.jpg"
        ),
        Movie(
            imdbID = "tt10399328",
            title = "Avengers Endgame: the Butt Plan",
            year = "2019",
            type = "movie",
            poster = "https://m.media-amazon.com/images/M/MV5BNTQ1OWQzODktMTY3Zi00OTQxLWExOTYtZTNjZjY5ZTY4M2UyXkEyXkFqcGdeQXVyMTAzMzk0NjAy._V1_SX300.jpg"
        ),
        Movie(
            imdbID = "tt16416424",
            title = "Avengers: Endgame (2019)",
            year = "2019",
            type = "movie",
            poster = "N/A"
        ),
    )
    return listMovie
}
