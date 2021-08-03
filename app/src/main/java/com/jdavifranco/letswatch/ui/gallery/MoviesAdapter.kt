package com.jdavifranco.letswatch.ui.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.databinding.GalleryFragmentBinding
import com.jdavifranco.letswatch.databinding.GalleryItemLayoutBinding

class MoviesAdapter:PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(DiffCallBackMovies()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = GalleryItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            Log.e("item", "${item.title}: $position ")
            holder.bind(item)
        }
    }

    class MoviesViewHolder(private val binding: GalleryItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Movie){
            binding.movie = item
            binding.executePendingBindings()
        }
    }
    class DiffCallBackMovies:DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}