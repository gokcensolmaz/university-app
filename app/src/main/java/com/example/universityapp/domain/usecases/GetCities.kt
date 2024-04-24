package com.example.universityapp.domain.usecases

import androidx.paging.PagingData
import com.example.universityapp.domain.model.City
import com.example.universityapp.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow

class GetCities (private val citiesRepository: CitiesRepository){
    operator fun invoke (pageNumber: Int): Flow<PagingData<City>> {
        return citiesRepository.getCities(pageNumber= pageNumber)
    }
}