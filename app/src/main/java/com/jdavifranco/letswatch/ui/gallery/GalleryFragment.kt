package com.jdavifranco.letswatch.ui.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.database.Genre
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.databinding.GalleryFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment : Fragment() {

    val viewModel: GalleryViewModel by viewModel()
    private lateinit var binding:GalleryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gallery_fragment, container, false)
        binding.lifecycleOwner =this
        val moviesAdapter = MoviesAdapter()
        binding.rvMovies.adapter = moviesAdapter
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            Log.e("movies size", "${it.size}")
            moviesAdapter.submitList(it)
        })

        moviesAdapter.rvPosition.observe(viewLifecycleOwner, Observer {
            Log.e("position", "$it")
            Log.e("posMUlt", "$it % 20 = ${it%20}")
            if(it%20==0){
                viewModel.getNextPage()
            }
        })

        return binding.root
    }


}
