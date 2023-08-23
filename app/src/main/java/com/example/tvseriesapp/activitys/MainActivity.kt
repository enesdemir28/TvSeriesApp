
package com.example.tvseriesapp.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tvseriesapp.adapter.TvSeriesAdapter
import com.example.tvseriesapp.databinding.ActivityMainBinding
import com.example.tvseriesapp.viewmodel.TvSeriesViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TvSeriesViewModel by viewModels()
    private lateinit var tvSeriesAdapter: TvSeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRv()
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
}