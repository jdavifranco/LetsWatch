package com.jdavifranco.letswatch.ui.gallery

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
import com.jdavifranco.letswatch.ui.details.DetailsActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_QUERY = "QUERY"

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by viewModel()
    private lateinit var binding:GalleryFragmentBinding
    private var searchJob: Job? = null
    private lateinit var query: String

    val mcl: MovieClickListener = object : MovieClickListener {
        override fun onItemClick(id: Long) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("MOVIE_ID", id)
            startActivity(intent)
        }
    }
    private val adapter = MoviesAdapter(mcl)

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchMovie(query).collect {
                adapter.submitData(it)
            }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_QUERY)?:"POPULAR"
        }
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
