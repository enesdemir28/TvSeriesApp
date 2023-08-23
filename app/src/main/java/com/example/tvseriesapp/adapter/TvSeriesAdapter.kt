package com.example.tvseriesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.tvseriesapp.databinding.TvSeriesLayoutAdapterBinding
import com.example.tvseriesapp.models.TvSeriesItem

class TvSeriesAdapter: RecyclerView.Adapter<TvSeriesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: TvSeriesLayoutAdapterBinding):
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TvSeriesItem>(){

        override fun areItemsTheSame(oldItem: TvSeriesItem, newItem: TvSeriesItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSeriesItem, newItem: TvSeriesItem): Boolean {
            return newItem == oldItem
        }

    }
    private val differ = AsyncListDiffer(this,diffCallback)
    var tvSeries:List<TvSeriesItem>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TvSeriesLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent , false
        ))
    }

    override fun getItemCount() = tvSeries.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvSeries = tvSeries[position]

        holder.binding.apply{
            textView.text = currentTvSeries.name
            imageView.load(currentTvSeries.image.original){

                crossfade(true)
                crossfade(1000)
            }
        }
    }
}