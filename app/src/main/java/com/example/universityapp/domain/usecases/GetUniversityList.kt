package com.example.universityapp.domain.usecases

import com.example.universityapp.data.local.University
import com.example.universityapp.domain.repository.UniversityAppRepository
import kotlinx.coroutines.flow.Flow

class GetUniversityList (

    private val universityAppRepository: UniversityAppRepository
) {
    operator fun invoke(): Flow<List<University>> {
        return universityAppRepository.getUniversityList()
    }
}