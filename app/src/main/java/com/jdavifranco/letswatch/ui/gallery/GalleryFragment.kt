package com.jdavifranco.letswatch.ui.gallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.jdavifranco.letswatch.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    val viewModel: GalleryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.gallery_fragment, container, false)
        val txtResponse:TextView = view.findViewById(R.id.txtResponse)
        viewModel.networkMovies.observe(viewLifecycleOwner, Observer {
            txtResponse.text = it.movies.size.toString()
        })
        return view
    }

}