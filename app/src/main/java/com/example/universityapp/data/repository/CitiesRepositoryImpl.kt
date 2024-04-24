package com.example.universityapp.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.universityapp.data.remote.CitiesPagingSource
import com.example.universityapp.data.remote.UniversityApi
import com.example.universityapp.domain.model.City
import com.example.universityapp.domain.model.University
import com.example.universityapp.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow

class CitiesRepositoryImpl(
    private val universityApi: UniversityApi
) : CitiesRepository {
    override fun getCities(pageNumber: Int): Flow<PagingData<City>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CitiesPagingSource(universityApi = universityApi, pageNumber = pageNumber)
            }
        ).flow
    }
}