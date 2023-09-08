
package com.example.tvseriesapp.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tvseriesapp.R
import com.example.tvseriesapp.adapter.TvSeriesAdapter
import com.example.tvseriesapp.databinding.ActivityMainBinding
import com.example.tvseriesapp.viewmodel.TvSeriesViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TvSeriesViewModel by viewModels()
    private lateinit var tvSeriesAdapter: TvSeriesAdapter
    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRv()
        initSearchView()
    }

    private fun setUpRv() {
        tvSeriesAdapter = TvSeriesAdapter()

        binding.rcvTvSeries.apply {
            adapter = tvSeriesAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
        }

        viewModel.responseTvSeries.observe(this, { listTvSeries ->
            tvSeriesAdapter.tvSeries = listTvSeries
        })
    }

    private fun initSearchView() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let { query ->
                    filterTvSeries(query)
                }
                return true
            }
        })
    }

    private fun filterTvSeries(query: String) {

        val tvSeriesList = viewModel.responseTvSeries.value
        if (tvSeriesList != null) {

            val filteredList = tvSeriesList.filter { tvSeries ->
                tvSeries.name.contains(query, ignoreCase = true)
            }
            tvSeriesAdapter.tvSeries = filteredList
        }
    }
}