package com.example.universityapp.domain.usecases

import androidx.paging.PagingData
import com.example.universityapp.domain.model.City
import com.example.universityapp.domain.repository.UniversityAppRepository
import kotlinx.coroutines.flow.Flow

class GetCities (private val universityAppRepository: UniversityAppRepository){
    operator fun invoke (pageNumber: Int): Flow<PagingData<City>> {
        return universityAppRepository.getCities(pageNumber= pageNumber)
    }
}