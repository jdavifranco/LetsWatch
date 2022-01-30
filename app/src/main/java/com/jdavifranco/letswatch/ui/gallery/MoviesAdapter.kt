package com.jdavifranco.letswatch.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jdavifranco.letswatch.databinding.GalleryItemLayoutBinding
import com.jdavifranco.letswatch.domain.model.Movie


class MoviesAdapter(var clickListener: MovieClickListener)
    :PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>
    (DiffCallBackMovies()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = GalleryItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context))

        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item, clickListener)
        }
    }

    class MoviesViewHolder(private val binding: GalleryItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Movie, clickListener: MovieClickListener){
            binding.movie = item
            binding.root.setOnClickListener { clickListener.onMovieClick(id = item.id) }
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