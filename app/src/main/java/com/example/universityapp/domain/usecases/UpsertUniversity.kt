package com.example.universityapp.domain.usecases

import com.example.universityapp.data.local.University
import com.example.universityapp.domain.repository.UniversityAppRepository

class UpsertUniversity(private val universityAppRepository: UniversityAppRepository) {
    suspend operator fun invoke(university: University) {
        universityAppRepository.upsertUniversity(university)
    }
}