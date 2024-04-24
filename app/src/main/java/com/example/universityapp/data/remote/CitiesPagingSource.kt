package com.example.universityapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.universityapp.domain.model.City

class CitiesPagingSource(
    private var universityApi: UniversityApi,
    private var pageNumber: Int
) : PagingSource<Int, City>() {
    private var totalCitiesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, City> {
        val page = params.key ?: 1
        return try {
            val citiesResponse = universityApi.getCities( pageNumber = page)
            totalCitiesCount += citiesResponse.data.size
            val cities = citiesResponse.data
            LoadResult.Page(
                data = cities,
                nextKey = if (totalCitiesCount == citiesResponse.total) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, City>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}