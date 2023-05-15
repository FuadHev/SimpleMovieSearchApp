package com.example.mynewfabproject.di

import android.app.Application
import com.example.mynewfabproject.repository.Repository
import com.example.mynewfabproject.retrofit.ApiUtils
import com.example.mynewfabproject.retrofit.WebApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getRepostory(apiService: WebApiService): Repository {
        return Repository(apiService)
    }


    @Provides
    @Singleton
    fun getApiService(): WebApiService {
        return ApiUtils.instance!!
    }


}