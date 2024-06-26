package com.example.universityapp.data.remote

import com.example.universityapp.data.remote.response.CitiesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UniversityApi {
    @GET("universities-at-turkey/page-{pageNumber}.json")
    suspend fun getCities(
        @Path("pageNumber") pageNumber: Int
    ): CitiesResponse

}