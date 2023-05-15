package com.example.mynewfabproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewfabproject.databinding.MovieItemBinding
import com.example.mynewfabproject.model.Search
import com.squareup.picasso.Picasso

class MovieAdapter(private val movieClickListener: MovieClickListener,private var movieList: List<Search>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(val view: MovieItemBinding) : RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = MovieItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    fun update(newList:List<Search>){
        this.movieList=newList
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        val b=holder.view

        b.movieTitle.text=movie.Title


        Picasso.get().load(movie.Poster).into(b.poster)


        b.poster.setOnClickListener {
            movieClickListener.movieClickListener(movie.imdbID)
        }


    }
}
interface MovieClickListener{
    fun movieClickListener(imdbId:String)
}