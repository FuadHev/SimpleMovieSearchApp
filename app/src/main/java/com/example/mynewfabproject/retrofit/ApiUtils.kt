package com.example.mynewfabproject.retrofit

object ApiUtils {
    val instance by lazy { RetrofitClient.getInstance().create(WebApiService::class.java)}
}