package com.jdavifranco.letswatch.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.databinding.GalleryFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
private const val ARG_QUERY = "POPULAR"

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by viewModel()
    private lateinit var binding:GalleryFragmentBinding
    private val adapter = MoviesAdapter()
    private var searchJob: Job? = null
    private var query: String = "POPULAR"

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchMovie(query).collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_QUERY)?:"POPULAR"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.gallery_fragment, container, false)
        binding.lifecycleOwner =this
        search(query)
        binding.rvMovies.adapter = adapter
        binding.rvMovies.setHasFixedSize(true)
        return binding.root
    }

    companion object{

        @JvmStatic
        fun newInstance(param1: String) =
            GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUERY, param1)
                }
            }
    }

}
