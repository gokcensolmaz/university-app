package com.example.universityapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.universityapp.domain.usecases.CitiesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
private val citiesUseCases : CitiesUseCases
) : ViewModel() {
    val cities = citiesUseCases.getCities(pageNumber = 1).cachedIn(viewModelScope)
}