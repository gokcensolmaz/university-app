package com.example.universityapp.domain.usecases

import com.example.universityapp.data.local.University
import com.example.universityapp.domain.repository.UniversityAppRepository

class GetUniversity (
    private val universityAppRepository: UniversityAppRepository
) {

    suspend operator fun invoke(name: String): University?{
        return universityAppRepository.getUniversity(name)
    }
}
