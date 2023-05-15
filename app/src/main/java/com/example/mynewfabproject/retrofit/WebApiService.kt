package com.example.mynewfabproject.retrofit

import com.example.mynewfabproject.model.MovieResponseById
import com.example.mynewfabproject.model.MovieSearchResponse
import com.example.mynewfabproject.model.NotFoundResponse
import com.example.mynewfabproject.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WebApiService {

    @Headers(
        "X-RapidAPI-Key:${Constant.API_KEY}",
        "X-RapidAPI-Host:${Constant.API_HOST}"
    )

    @GET("/")
    suspend fun searchMovie(
        @Query("s") search: String,
        @Query("r") format: String = "json",
        @Query("page") page: Int = 1
    ): Response<MovieSearchResponse>


    @GET("/")
    suspend fun searchMovieError(
        @Query("s") search: String,
        @Query("r") format: String = "json",
        @Query("page") page: Int = 1
    ): Response<NotFoundResponse>


//    @GET("/")
//    suspend fun searchMovie(
//        @Query("s") search: String,
//        @Query("r") format: String = "json",
//        @Query("page") page: Int = 1,
//        @Header("X-RapidAPI-Key"), key: String = "api key here",
//        @Header("X-RapidAPI-Host") host: String = "movie-database-alternative.p.rapidapi.com")
//    ): Response<MovieSearchResponse>



    @Headers(
        "X-RapidAPI-Key:${Constant.API_KEY}",
        "X-RapidAPI-Host:movie-database-alternative.p.rapidapi.com"
    )
    @GET("/")
    suspend fun getMovieById(
        @Query("r") format: String = "json",
        @Query("i") imdbId: String
    ): Response<MovieResponseById>
}