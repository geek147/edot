package com.example.edot.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.edot.dispatchers.CoroutineDispatchers
import com.example.edot.ui.base.BaseViewModel
import com.example.edot.usecase.SearchMovieUseCase
import com.example.edot.utils.Intent
import com.example.edot.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.edot.utils.Result
import com.example.edot.utils.ViewState

@HiltViewModel
class SharedViewModel @Inject constructor (
    private val searchMovieUseCase: SearchMovieUseCase,
    private val ioDispatchers: CoroutineDispatchers
)  : BaseViewModel<Intent, State>(State()){
    override fun onIntentReceived(intent: Intent) {
        when (intent) {
            is Intent.searchMovie -> searchMovie(intent.query)
            else -> {}
        }
    }

    private fun searchMovie(query : String) {
        setState {
            copy(
                showLoading = true,
            )
        }

        val param = SearchMovieUseCase.Params(query,1)

        viewModelScope.launch {
            when (
                val result = withContext(ioDispatchers.io) {
                    searchMovieUseCase(param)
                }
            ) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        setState {
                            copy(
                                listSearchResult = emptyList(),
                                showLoading = false,
                                viewState = ViewState.EmptyListSearch
                            )
                        }
                    } else {
                        setState {
                            copy(
                                listSearchResult = result.data,
                                showLoading = false,
                                viewState = ViewState.SuccessSearch
                            )
                        }
                    }
                }
                is Result.Error -> {
                    setState {
                        copy(
                            listSearchResult = emptyList(),
                            showLoading = false,
                            viewState = ViewState.Error
                        )
                    }
                }

                else -> {}
            }
        }
    }
}