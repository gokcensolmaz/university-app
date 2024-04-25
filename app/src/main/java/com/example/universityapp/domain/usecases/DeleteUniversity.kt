package com.example.universityapp.domain.usecases

import com.example.universityapp.data.local.University
import com.example.universityapp.domain.repository.UniversityAppRepository

class DeleteUniversity(
    private val universityAppRepository: UniversityAppRepository
) {
    suspend operator fun invoke(university: University){
        universityAppRepository.deleteUniversity(university)
    }
}