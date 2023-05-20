package com.example.mynewfabproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewfabproject.model.NotFoundResponse
import com.example.mynewfabproject.model.Search
import com.example.mynewfabproject.repository.Repository
import com.example.mynewfabproject.retrofit.ApiUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo:Repository):ViewModel() {
    val moviesLiveData= MutableLiveData<List<Search>>()
    val errorLivedata=MutableLiveData<String>()
    val isLoading=MutableLiveData<Boolean>()
    fun searchMovie(search_Title:String){
        viewModelScope.launch(Dispatchers.IO){
            val response=repo.searchMovie(search_Title)
            isLoading.postValue(true)
            if (response.isSuccessful&&response.code()==200){
                val  body=response.body()
                isLoading.postValue(false)
                // sende bu olmayacaq tekce else olan yeri yaz fso//else-nin icin
                if(!body?.Response.toBoolean()){
                    if (response.isSuccessful){
                        response.body()?.Response?.let { Log.e("error", it) }
                        response.body()?.Error?.let { Log.e("error", it) }
                        errorLivedata.postValue(response.body()?.Error ?:"error")
                    }
                }else{

                    if (body?.Search != null) {
                        moviesLiveData.postValue(body.Search)
                    }
                }


            }
        }

    }

}