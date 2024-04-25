package com.example.universityapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversityDao {
    @Upsert
    suspend fun upsertUniversity(university: University)

    @Delete
    suspend fun deleteUniversity(university: University)

    @Query("SELECT * FROM University")
    fun getUniversityList(): Flow<List<University>>
    @Query("SELECT * FROM University WHERE name = :name")
    suspend fun getUniversity(name: String): University?

}