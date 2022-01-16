package com.jdavifranco.letswatch.views.moviegallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.databinding.GalleryFragmentBinding
import com.jdavifranco.letswatch.ui.moviegallery.GalleryViewModel
import com.jdavifranco.letswatch.views.moviedetails.DetailsActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class GalleryFragment : Fragment() {

    private var searchJob: Job? = null
    private lateinit var searchQuery: String

    private val viewModel: GalleryViewModel by viewModel()
    private lateinit var binding:GalleryFragmentBinding
    private val adapter = MoviesAdapter(movieClickListener())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.gallery_fragment, container, false)
        binding.lifecycleOwner =this

        searchMovies(searchQuery)

        binding.rvMovies.adapter = adapter
        binding.rvMovies.setHasFixedSize(true)

        return binding.root
    }

    private fun searchMovies(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchMovies(query).collect {
                adapter.submitData(it)
            }
        }
    }

    private fun movieClickListener():MovieClickListener{

        val movieClickListener: MovieClickListener = object : MovieClickListener {
            override fun onMovieClick(id: Long) {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.DETAILS_QUERY_ARG, id)
                startActivity(intent)
            }
        }

        return movieClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchQuery = it.getString(SEARCH_QUERY_ARG)?: INITIAL_SEARCH_QUERY
        }
    }

    companion object{
        const val INITIAL_SEARCH_QUERY = "POPULAR"
        const val SEARCH_QUERY_ARG = "SEARCH_QUERY_ARG"

        @JvmStatic
        fun newInstance(param1: String) =
            GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString(SEARCH_QUERY_ARG, param1)
                }
            }
    }

}
