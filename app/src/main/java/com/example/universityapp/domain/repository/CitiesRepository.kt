package com.example.universityapp.domain.repository

import androidx.paging.PagingData
import com.example.universityapp.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getCities(pageNumber: Int): Flow<PagingData<City>>
}