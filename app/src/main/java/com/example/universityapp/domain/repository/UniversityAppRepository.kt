package com.example.universityapp.domain.repository

import androidx.paging.PagingData
import com.example.universityapp.data.local.University
import com.example.universityapp.domain.model.City
import kotlinx.coroutines.flow.Flow

interface UniversityAppRepository {
    fun getCities(pageNumber: Int): Flow<PagingData<City>>

    suspend fun upsertUniversity(university: University)
    suspend fun deleteUniversity(university: University)
    fun getUniversityList(): Flow<List<University>>

    suspend fun getUniversity(name: String) : University?
}