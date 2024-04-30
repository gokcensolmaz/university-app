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

}