package com.example.edot.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies(
    @Json(name = "Response")
    val response: String,
    @Json(name = "Search")
    val search: List<Movie>?,
    @Json(name = "totalResults")
    val totalResults: String
) {
    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "imdbID")
        val imdbID: String?,
        @Json(name = "Poster")
        val poster: String?,
        @Json(name = "Title")
        val title: String?,
        @Json(name = "Type")
        val type: String?,
        @Json(name = "Year")
        val year: String?
    )
}