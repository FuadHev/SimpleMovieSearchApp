package com.example.mynewfabproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewfabproject.model.MovieResponseById
import com.example.mynewfabproject.model.NotFoundResponse
import com.example.mynewfabproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(val repo:Repository)  :ViewModel() {
    val movieLiveData=MutableLiveData<MovieResponseById>()



    fun getMovieById(imdbId:String){
        val job=viewModelScope.launch(Dispatchers.IO) {
            val response=repo.getMovieById(imdbId)
            if (response.isSuccessful){
                val body=response.body()
                body?.let { movieLiveData.postValue(it) }
            }


        }
    }
}