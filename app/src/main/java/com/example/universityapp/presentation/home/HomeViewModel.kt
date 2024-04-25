package com.example.universityapp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.universityapp.data.local.University
import com.example.universityapp.domain.usecases.UniversityAppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val universityAppUseCases: UniversityAppUseCases
) : ViewModel() {
    val cities = universityAppUseCases.getCities(pageNumber = 1).cachedIn(viewModelScope)
    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: AddRemoveFavoriteEvent) {
        when (event) {
            is AddRemoveFavoriteEvent.UpsertDeleteUniversity -> {
                viewModelScope.launch {
                    val university = universityAppUseCases.getUniversity(event.university.name)
                    if (university == null) {
                        upsertUniversity(event.university)

                    } else {
                        deleteUniversity(event.university)
                    }
                }
            }

            is AddRemoveFavoriteEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun upsertUniversity(university: University) {
        universityAppUseCases.upsertUniversity(university = university)
        sideEffect = "University Saved"
    }

    private suspend fun deleteUniversity(university: University) {
        universityAppUseCases.deleteUniversity(university = university)
        sideEffect = "University Deleted"
    }
}