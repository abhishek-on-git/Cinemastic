package com.wcp.cinemastic.presentation.artists

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcp.cinemastic.R
import com.wcp.cinemastic.databinding.ActivityPopularArtistsBinding
import com.wcp.cinemastic.presentation.adapters.PopularArtistsAdapter
import com.wcp.cinemastic.presentation.di.Injector
import javax.inject.Inject

class PopularArtistsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPopularArtistsBinding

    private lateinit var artistRecyclerViewAdapter: PopularArtistsAdapter

    @Inject
    lateinit var factory: PopularArtistsViewModelFactory
    private lateinit var popularArtistsViewModel: PopularArtistsViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popular_artists)
        (application as Injector).buildPopularArtistsSubComponent().inject(this)

        popularArtistsViewModel = ViewModelProvider(this, factory)
            .get(PopularArtistsViewModel::class.java)

        adjustForSystemBounds()
        setupRecyclerView()
        displayPopularArtists()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.refresh_menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.refresh_menu_item -> {
                refreshArtistList()
                true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun refreshArtistList(){
        binding.artistProgressBar.visibility = View.VISIBLE
        val freshArtistsLiveData = popularArtistsViewModel.updatePopularArtists()
        freshArtistsLiveData.observe(this, Observer {
            if(it != null) {
                artistRecyclerViewAdapter.setPopularArtistList(it)
                artistRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Unable to update. Try again", Toast.LENGTH_SHORT).show()
            }
            binding.artistProgressBar.visibility = View.GONE
        })
    }

    private fun setupRecyclerView() {
        binding.popularArtistRecyclerView.layoutManager = LinearLayoutManager(this)
        artistRecyclerViewAdapter = PopularArtistsAdapter()
        binding.popularArtistRecyclerView.adapter = artistRecyclerViewAdapter
    }

    private fun displayPopularArtists() {
        binding.artistProgressBar.visibility = View.VISIBLE
        binding.popularArtistRecyclerView.visibility = View.INVISIBLE
        val artistLiveData = popularArtistsViewModel.fetchPopularArtists()
        artistLiveData.observe(this, Observer {
            if (it != null) {
                artistRecyclerViewAdapter.setPopularArtistList(it)
                artistRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Something went wrong. Try again", Toast.LENGTH_SHORT).show()
            }
            binding.popularArtistRecyclerView.visibility = View.VISIBLE
            binding.artistProgressBar.visibility = View.GONE
        })
    }

    fun adjustForSystemBounds(){
        val view = binding.popularArtistRecyclerView
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.setMargins(params.leftMargin, getTopMargin(this), params.rightMargin, params.bottomMargin)
        //view.setPadding(0,0,0,getBottomMargin(this))
    }

    fun getTopMargin(context: Context) : Int{
        var statusBarHeight = 0
        var actionBarHeight: Int = 0

        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if(resId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resId)
        }

        val tv = TypedValue()
        if(this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }

        return statusBarHeight + actionBarHeight + resources.getDimensionPixelSize(R.dimen.default_top_margin)
    }
}