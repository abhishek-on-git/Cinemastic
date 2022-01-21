package com.wcp.cinemastic.presentation.movies

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
import com.wcp.cinemastic.databinding.ActivityPopularMovieBinding
import com.wcp.cinemastic.presentation.adapters.PopularMoviesAdapter
import com.wcp.cinemastic.presentation.di.Injector
import javax.inject.Inject

class PopularMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPopularMovieBinding
    private lateinit var movieRecyclerViewAdapter: PopularMoviesAdapter

    @Inject
    lateinit var factory: PopularMovieViewModelFactory
    private lateinit var popularMoviesViewModel: PopularMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popular_movie)
        (application as Injector).buildPopularMoviesSubComponent().inject(this)

        popularMoviesViewModel = ViewModelProvider(this, factory)
            .get(PopularMovieViewModel::class.java)

        adjustForSystemBounds()
        setupRecyclerView()
        displayPopularMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.refresh_menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.refresh_menu_item -> {
                refreshMovieList()
                true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun refreshMovieList(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val freshMoviesLiveData = popularMoviesViewModel.updatePopularMovies()
        freshMoviesLiveData.observe(this, Observer {
            if(it != null) {
                movieRecyclerViewAdapter.setPopularMovieList(it)
                movieRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Unable to update. Try again", Toast.LENGTH_SHORT).show()
            }
            binding.movieProgressBar.visibility = View.GONE
        })
    }

    private fun setupRecyclerView() {
        binding.popularMovieRecyclerView.layoutManager = LinearLayoutManager(this)
        movieRecyclerViewAdapter = PopularMoviesAdapter()
        binding.popularMovieRecyclerView.adapter = movieRecyclerViewAdapter
    }

    private fun displayPopularMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        binding.popularMovieRecyclerView.visibility = View.INVISIBLE
        val movieLiveData = popularMoviesViewModel.fetchPopularMovies()
        movieLiveData.observe(this, Observer {
            if (it != null) {
                movieRecyclerViewAdapter.setPopularMovieList(it)
                movieRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Something went wrong. Try again", Toast.LENGTH_SHORT).show()
            }
            binding.popularMovieRecyclerView.visibility = View.VISIBLE
            binding.movieProgressBar.visibility = View.GONE
        })
    }

    fun adjustForSystemBounds(){
        val view = binding.popularMovieRecyclerView
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.setMargins(params.leftMargin, getTopMargin(this), params.rightMargin, params.bottomMargin)
        //view.setPadding(0,0,0,getBottomMargin(this))
    }

    fun getBottomMargin(context: Context) : Int{
        var result = 0
        val resId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if(resId > 0) {
            result = context.resources.getDimensionPixelSize(resId)
        }
        return result
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