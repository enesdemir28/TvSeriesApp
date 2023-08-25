package com.example.tvseriesapp.api

import com.example.tvseriesapp.utils.Constants
import com.example.tvseriesapp.models.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getTvSeries(): Response<TvSeriesResponse>
}