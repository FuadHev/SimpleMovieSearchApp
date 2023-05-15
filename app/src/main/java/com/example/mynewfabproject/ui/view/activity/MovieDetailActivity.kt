package com.example.mynewfabproject.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mynewfabproject.databinding.ActivityMovieDetailBinding
import com.example.mynewfabproject.ui.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[MovieDetailViewModel::class.java]
        binding= ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imbdId=intent.getStringExtra("imdbId") as String
        Log.e("id",imbdId)

        viewModel.getMovieById(imbdId)

        viewModel.movieLiveData.observe(this){
            binding.movieName.text=it.Title
            binding.plot.text=it.Plot
            binding.year.text=it.Year
            binding.rating.text=it.Ratings[0].Value
            Picasso.get().load(it.Poster).into(binding.poster)
        }






    }
}