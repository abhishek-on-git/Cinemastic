package com.wcp.cinemastic.presentation.shows

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
import com.wcp.cinemastic.databinding.ActivityPopularShowBinding
import com.wcp.cinemastic.presentation.adapters.PopularShowsAdapter
import com.wcp.cinemastic.presentation.di.Injector
import com.wcp.cinemastic.presentation.di.core.ApplicationComponent
import javax.inject.Inject

class PopularShowActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPopularShowBinding
    private lateinit var showRecyclerViewAdapter: PopularShowsAdapter

    @Inject
    lateinit var factory: PopularShowViewModelFactory
    private lateinit var popularShowViewModel: PopularShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popular_show)
        (application as Injector).buildPopularShowsSubComponent().inject(this)

        popularShowViewModel = ViewModelProvider(this, factory)
            .get(PopularShowViewModel::class.java)

        adjustForSystemBounds()
        setupRecyclerView()
        displayPopularShows()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.refresh_menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.refresh_menu_item -> {
                refreshShowList()
                true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun refreshShowList(){
        binding.showProgressBar.visibility = View.VISIBLE
        val freshShowsLiveData = popularShowViewModel.updatePopularShows()
        freshShowsLiveData.observe(this, Observer {
            if(it != null) {
                showRecyclerViewAdapter.setPopularShowList(it)
                showRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Unable to update. Try again", Toast.LENGTH_SHORT).show()
            }
            binding.showProgressBar.visibility = View.GONE
        })
    }

    private fun setupRecyclerView() {
        binding.popularShowRecyclerView.layoutManager = LinearLayoutManager(this)
        showRecyclerViewAdapter = PopularShowsAdapter()
        binding.popularShowRecyclerView.adapter = showRecyclerViewAdapter
    }

    private fun displayPopularShows() {
        binding.showProgressBar.visibility = View.VISIBLE
        binding.popularShowRecyclerView.visibility = View.INVISIBLE
        val showLiveData = popularShowViewModel.fetchPopularShows()
        showLiveData.observe(this, Observer {
            if (it != null) {
                showRecyclerViewAdapter.setPopularShowList(it)
                showRecyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Something went wrong. Try again", Toast.LENGTH_SHORT).show()
            }
            binding.popularShowRecyclerView.visibility = View.VISIBLE
            binding.showProgressBar.visibility = View.GONE
        })
    }

    fun adjustForSystemBounds(){
        val view = binding.popularShowRecyclerView
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