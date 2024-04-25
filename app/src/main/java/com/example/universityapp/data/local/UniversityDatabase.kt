package com.example.universityapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [University::class], version=1)
abstract class UniversityDatabase : RoomDatabase(){
    abstract val universityDao: UniversityDao
}