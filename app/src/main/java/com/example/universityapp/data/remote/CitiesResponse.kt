package com.example.universityapp.data.remote

import com.example.universityapp.domain.model.City

data class CitiesResponse(
    val currentPage: Int,
    val data: List<City>,
    val itemPerPage: Int,
    val pageSize: Int,
    val total: Int,
    val totalPage: Int
)