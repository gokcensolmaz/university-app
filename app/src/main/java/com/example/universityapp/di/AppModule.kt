package com.example.universityapp.di

import android.app.Application
import androidx.room.Room
import com.example.universityapp.data.local.UniversityDao
import com.example.universityapp.data.local.UniversityDatabase
import com.example.universityapp.data.remote.UniversityApi
import com.example.universityapp.data.repository.UniversityAppRepositoryImpl
import com.example.universityapp.domain.repository.UniversityAppRepository
import com.example.universityapp.domain.usecases.DeleteUniversity
import com.example.universityapp.domain.usecases.UniversityAppUseCases
import com.example.universityapp.domain.usecases.GetCities
import com.example.universityapp.domain.usecases.GetUniversity
import com.example.universityapp.domain.usecases.GetUniversityList
import com.example.universityapp.domain.usecases.UpsertUniversity
import com.example.universityapp.util.Constants.BASE_URL
import com.example.universityapp.util.Constants.UNIVERSITY_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUniversityApi(): UniversityApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCitiesRepository(
        universityApi: UniversityApi,
        universityDao: UniversityDao
    ): UniversityAppRepository = UniversityAppRepositoryImpl(universityApi,universityDao)

    @Provides
    @Singleton
    fun providesCitiesUseCases(universityAppRepository: UniversityAppRepository): UniversityAppUseCases{
        return UniversityAppUseCases(
            getCities = GetCities(universityAppRepository),
            upsertUniversity = UpsertUniversity(universityAppRepository),
            deleteUniversity = DeleteUniversity(universityAppRepository),
            getUniversityList = GetUniversityList(universityAppRepository),
            getUniversity = GetUniversity(universityAppRepository)
        )
    }

    @Provides
    @Singleton
    fun provideUniversityDatabase(
        application: Application
    ): UniversityDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = UniversityDatabase::class.java,
            name = UNIVERSITY_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUniversityDao(
        universityDatabase: UniversityDatabase
    ): UniversityDao = universityDatabase.universityDao
}