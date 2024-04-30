package com.example.universityapp.presentation.common.CityExpandableCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.example.universityapp.domain.usecases.UniversityAppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityCardViewModel @Inject constructor(
    private val universityAppUseCases: UniversityAppUseCases
):ViewModel() {
    private val _expandedCities = mutableStateOf(mapOf<Int, Boolean>())

    fun toggleCityExpanded(cityId: Int) {
        val currentStates = _expandedCities.value.toMutableMap()
        val currentState = currentStates[cityId] ?: false
        currentStates[cityId] = !currentState
        _expandedCities.value = currentStates
    }

    fun isCityExpanded(cityId: Int): Boolean = _expandedCities.value[cityId] ?: false

}