package com.wcp.cinemastic.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.wcp.cinemastic.R
import com.wcp.cinemastic.databinding.ActivityHomeBinding
import com.wcp.cinemastic.presentation.artists.PopularArtistsActivity
import com.wcp.cinemastic.presentation.movies.PopularMovieActivity
import com.wcp.cinemastic.presentation.shows.PopularShowActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setOnClickListeners()
        adjustForNavBar()
    }

    fun setOnClickListeners() {
        binding.fetchMoviesButton.setOnClickListener {
            val intent = Intent(this, PopularMovieActivity::class.java)
            startActivity(intent)
        }
        binding.fetchShowsButton.setOnClickListener {
            val intent = Intent(this, PopularShowActivity::class.java)
            startActivity(intent)
        }
        binding.fetchArtistsButton.setOnClickListener {
            val intent = Intent(this, PopularArtistsActivity::class.java)
            startActivity(intent)
        }
    }

    fun adjustForNavBar(){
        val artistButton = findViewById<Button>(R.id.fetchArtistsButton)
        val params = artistButton.layoutParams as ConstraintLayout.LayoutParams
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, getNavBarHeight(this))
    }

    fun getNavBarHeight(context: Context) : Int{
        var result = 0
        val resId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if(resId > 0) {
            result = context.resources.getDimensionPixelSize(resId)
        }
        return result
    }
}