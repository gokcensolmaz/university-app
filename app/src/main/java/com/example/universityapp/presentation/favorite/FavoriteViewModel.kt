package com.example.universityapp.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.domain.usecases.UniversityAppUseCases
import com.example.universityapp.presentation.common.UniversityCard.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val universityAppUseCases: UniversityAppUseCases
) : ViewModel() {
    private val _state = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        universityAppUseCases.getUniversityList().onEach {
            _state.value = _state.value.copy(universities = it.asReversed())
        }.launchIn(viewModelScope)
    }
}