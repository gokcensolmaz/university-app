package com.example.universityapp.presentation.common.UniversityCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityapp.data.local.University
import com.example.universityapp.domain.usecases.UniversityAppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val universityAppUseCases: UniversityAppUseCases
): ViewModel(){

    // EXPANDED STATE OPERATIONS
    private val _expandedUniversities = mutableStateOf(mapOf<String, Boolean>())

    fun toggleUniversityExpanded(universityName: String) {
        val currentStates = _expandedUniversities.value.toMutableMap()
        val currentState = currentStates[universityName] ?: false
        currentStates[universityName] = !currentState
        _expandedUniversities.value = currentStates
    }

    fun isUniversityExpanded(universityName: String): Boolean = _expandedUniversities.value[universityName] ?: false

    // FAVORITE STATE OPERATIONS

    private val _favoriteUniversities = mutableStateOf(FavoriteState())
    init {
        getUniversities()
    }
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
            is AddRemoveFavoriteEvent.RemoveSideEffect ->{
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

    fun isUniversityFavorite(university: University): Boolean = _favoriteUniversities.value.universities.contains(university)?: false

    private fun getUniversities() {
        universityAppUseCases.getUniversityList().onEach {
            _favoriteUniversities.value = _favoriteUniversities.value.copy(universities = it.asReversed())
        }.launchIn(viewModelScope)
    }
}

