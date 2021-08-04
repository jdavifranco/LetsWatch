package com.jdavifranco.letswatch.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.jdavifranco.letswatch.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        val movieId = intent.getLongExtra("MOVIE_ID", -1)
        if(movieId!=-1L){
            Log.e("movieID", "$movieId")
            viewModel.resfreshDetailsMovies(movieId)
            viewModel.movie.observe(this, Observer {
                binding.movie = it
            })
        }
        setContentView(binding.root)
    }
}