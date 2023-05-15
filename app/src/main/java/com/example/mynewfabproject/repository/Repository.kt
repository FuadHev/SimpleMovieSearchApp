package com.example.mynewfabproject.repository

import com.example.mynewfabproject.model.MovieResponseById
import com.example.mynewfabproject.model.MovieSearchResponse
import com.example.mynewfabproject.model.NotFoundResponse
import com.example.mynewfabproject.retrofit.ApiUtils
import com.example.mynewfabproject.retrofit.WebApiService
import retrofit2.Response

class Repository (private val apiService: WebApiService) {


    suspend fun searchMovie(search_title:String): Response<MovieSearchResponse> {
        return apiService.searchMovie(search_title)
    }


    suspend fun searchMovieError(search_Title:String): Response<NotFoundResponse> {
        return apiService.searchMovieError(search_Title)
    }

    suspend fun getMovieById(imdbId:String): Response<MovieResponseById> {
        return apiService.getMovieById(imdbId=imdbId)
    }
}