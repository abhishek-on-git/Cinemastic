package com.wcp.cinemastic.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wcp.cinemastic.BuildConfig
import com.wcp.cinemastic.R
import com.wcp.cinemastic.data.model.movies.PopularMovie
import com.wcp.cinemastic.data.model.shows.PopularShow
import com.wcp.cinemastic.databinding.PopularMovieListItemBinding
import com.wcp.cinemastic.databinding.PopularShowListItemBinding

class PopularShowsAdapter: RecyclerView.Adapter<PopularShowsAdapter.PopShowViewHolder>() {
    private val popularShowsList = ArrayList<PopularShow>()

    fun setPopularShowList(showList: List<PopularShow>) {
        popularShowsList.clear()
        popularShowsList.addAll(showList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PopularShowListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.popular_show_list_item,
            parent,
            false
        )
        return PopularShowsAdapter.PopShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopShowViewHolder, position: Int) {
        holder.bind(popularShowsList[position])
    }

    override fun getItemCount(): Int {
        return popularShowsList.size
    }

    class PopShowViewHolder(
        val binding: PopularShowListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popShow: PopularShow) {
            Log.i("Abhishek", "title: ${popShow.name}")
            binding.titleView.text = popShow.name
            binding.descriptionView.text = popShow.overview
            val posterUrl = BuildConfig.POSTER_URL + popShow.posterPath
            Glide.with(binding.posterView.context)
                .load(posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.posterView)
        }

    }
}