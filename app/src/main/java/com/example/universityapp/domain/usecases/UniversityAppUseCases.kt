package com.example.universityapp.domain.usecases

data class UniversityAppUseCases (
    val getCities: GetCities,
    val upsertUniversity: UpsertUniversity,
    val deleteUniversity: DeleteUniversity,
    val getUniversityList: GetUniversityList,
    val getUniversity: GetUniversity
)