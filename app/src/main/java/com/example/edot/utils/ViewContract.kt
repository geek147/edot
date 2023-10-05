package com.example.edot.utils

import com.example.edot.model.Movies.Movie


sealed class Intent {
    data class searchMovie(val query: String): Intent()
}

data class State(
    val showLoading: Boolean = false,
    val listSearchResult: List<Movie> = listOf(),
    val viewState: ViewState = ViewState.Idle
)

sealed class ViewState {
    object Idle : ViewState()
    object SuccessSearch: ViewState()
    object EmptyListSearch : ViewState()
    object Error: ViewState()
}
