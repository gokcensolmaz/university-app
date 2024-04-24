package com.example.universityapp.di

import com.example.universityapp.data.remote.UniversityApi
import com.example.universityapp.data.repository.CitiesRepositoryImpl
import com.example.universityapp.domain.repository.CitiesRepository
import com.example.universityapp.domain.usecases.CitiesUseCases
import com.example.universityapp.domain.usecases.GetCities
import com.example.universityapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
        universityApi: UniversityApi
    ): CitiesRepository = CitiesRepositoryImpl(universityApi)

    @Provides
    @Singleton
    fun providesCitiesUseCases(citiesRepository: CitiesRepository): CitiesUseCases{
        return CitiesUseCases(
            getCities = GetCities(citiesRepository)
        )
    }
}