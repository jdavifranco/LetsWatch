package com.jdavifranco.letswatch.ui.moviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jdavifranco.letswatch.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {


    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        val detailsQuery = intent.getLongExtra(DETAILS_QUERY_ARG, DETAILS_DEFAULT_QUERY_ARG)

        if(detailsQuery!= DETAILS_DEFAULT_QUERY_ARG) {
            viewModel.refreshDetails(detailsQuery)
            viewModel.details.observe(this, {
                binding.details = it
            })
        }

        setContentView(binding.root)
    }

    companion object{
        const val DETAILS_QUERY_ARG = "DETAILS_QUERY_ARG"
        const val DETAILS_DEFAULT_QUERY_ARG = -1L

    }


}