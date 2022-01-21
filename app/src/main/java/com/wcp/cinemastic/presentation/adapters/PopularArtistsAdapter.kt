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
import com.wcp.cinemastic.data.model.artists.PopularArtist
import com.wcp.cinemastic.databinding.PopularArtistListItemBinding
import com.wcp.cinemastic.util.Constants

class PopularArtistsAdapter: RecyclerView.Adapter<PopularArtistsAdapter.PopArtistViewHolder>() {
    
    private val popularArtistList = ArrayList<PopularArtist>()

    fun setPopularArtistList(artistList: List<PopularArtist>) {
        popularArtistList.clear()
        popularArtistList.addAll(artistList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopArtistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PopularArtistListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.popular_artist_list_item,
            parent,
            false
        )
        return PopularArtistsAdapter.PopArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopArtistViewHolder, position: Int) {
        holder.bind(popularArtistList[position])
    }

    override fun getItemCount(): Int {
        return popularArtistList.size
    }

    class PopArtistViewHolder(
        val binding: PopularArtistListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popArtist: PopularArtist) {
            binding.titleView.text = popArtist.name
            binding.descriptionView.text = Constants.POPULARITY_INDEX_STRING + popArtist.popularity.toString()
            val posterUrl = BuildConfig.POSTER_URL + popArtist.profilePath
            Glide.with(binding.posterView.context)
                .load(posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.posterView)
        }
    }
}