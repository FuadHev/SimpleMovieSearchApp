package com.example.mynewfabproject.di

import com.example.mynewfabproject.repository.Repository
import com.example.mynewfabproject.retrofit.WebApiService
import com.example.mynewfabproject.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun getApiService(retrofit: Retrofit): WebApiService {
        return retrofit.create(WebApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit():Retrofit{

        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY

        val clientBuilder= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

       return Retrofit.Builder().baseUrl(Constant.BASE_URL)
             .addConverterFactory(MoshiConverterFactory.create())
             .client(clientBuilder)
             .build()
    }


}