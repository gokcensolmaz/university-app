package com.example.universityapp.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.universityapp.data.local.University
import com.example.universityapp.data.local.UniversityDao
import com.example.universityapp.data.remote.CitiesPagingSource
import com.example.universityapp.data.remote.UniversityApi
import com.example.universityapp.domain.model.City
import com.example.universityapp.domain.repository.UniversityAppRepository
import kotlinx.coroutines.flow.Flow

class UniversityAppRepositoryImpl(
    private val universityApi: UniversityApi,
    private val universityDao: UniversityDao
) : UniversityAppRepository {
    override fun getCities(pageNumber: Int): Flow<PagingData<City>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CitiesPagingSource(universityApi = universityApi, pageNumber = pageNumber)
            }
        ).flow
    }

    override suspend fun upsertUniversity(university: University) {
        universityDao.upsertUniversity(university)
    }

    override suspend fun deleteUniversity(university: University) {
        universityDao.deleteUniversity(university)
    }

    override fun getUniversityList(): Flow<List<University>> {
        return universityDao.getUniversityList()
    }

    override suspend fun getUniversity(name: String): University? {
        return universityDao.getUniversity(name)
    }
}