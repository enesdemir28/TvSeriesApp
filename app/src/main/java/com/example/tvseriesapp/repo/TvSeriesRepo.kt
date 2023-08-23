package com.example.tvseriesapp.repo

import com.example.tvseriesapp.api.ApiService
import javax.inject.Inject

class TvSeriesRepo @Inject
constructor(private val apiService : ApiService){

    suspend fun getTvSeries() = apiService.getTvSeries()
}