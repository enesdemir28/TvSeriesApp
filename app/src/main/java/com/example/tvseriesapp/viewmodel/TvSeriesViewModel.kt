package com.example.tvseriesapp.viewmodel

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvseriesapp.models.TvSeriesItem
import com.example.tvseriesapp.repo.TvSeriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel
@Inject
constructor(private val repository: TvSeriesRepo) : ViewModel() {

    private val _response = MutableLiveData<List<TvSeriesItem>>()
    val responseTvSeries: LiveData<List<TvSeriesItem>>
        get() = _response

    init {
        getAllTvSeries()
    }


    private fun getAllTvSeries() = viewModelScope.launch {
        repository.getTvSeries().let { response ->

        if(response.isSuccessful){
            _response.postValue(response.body())
        } else{
            Log.d(TAG,"getAllTvSeries Error: ${response.code()}")
        }

        }
    }

}