package com.wcp.cinemastic.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wcp.cinemastic.BuildConfig
import com.wcp.cinemastic.R
import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.databinding.PopularMovieListItemBinding

class PopularMoviesAdapter: RecyclerView.Adapter<PopularMoviesAdapter.PopMovieViewHolder>() {

    private val popularMovieList = ArrayList<PopularMovie>()

    fun setPopularMovieList(movieList: List<PopularMovie>) {
        popularMovieList.clear()
        popularMovieList.addAll(movieList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PopularMovieListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.popular_movie_list_item,
            parent,
            false
        )
        return PopMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopMovieViewHolder, position: Int) {
        holder.bind(popularMovieList[position])
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }

    class PopMovieViewHolder(
        val binding: PopularMovieListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popMovie: PopularMovie) {
            Log.i("Abhishek", "title: ${popMovie.title}")
            binding.titleView.text = popMovie.title
            binding.descriptionView.text = popMovie.overview
            val posterUrl = BuildConfig.POSTER_URL + popMovie.posterPath
            Glide.with(binding.posterView.context)
                .load(posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.posterView)
        }
    }
}