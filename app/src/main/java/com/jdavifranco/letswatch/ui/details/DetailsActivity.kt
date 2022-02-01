package com.jdavifranco.letswatch.ui.details

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.databinding.ActivityDetailsBinding
import com.jdavifranco.letswatch.ui.utils.ResponseState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {


    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        val detailsQuery = intent.getLongExtra(DETAILS_QUERY_ARG, DETAILS_DEFAULT_QUERY_ARG)
        viewModel.fetchDetails(detailsQuery)

        viewModel.responseState.observe(this, {responseState ->
            binding.responseState = responseState
            when(responseState){
                is ResponseState.Loading ->{}
                is ResponseState.Error->{
                    binding.errorState.findViewById<Button>(R.id.btnReload).setOnClickListener {
                        viewModel.fetchDetails(detailsQuery)
                    }
                }
                is ResponseState.Success->{
                    binding.details = responseState.result
                }
            }

        })

        setContentView(binding.root)
    }

    companion object{
        const val DETAILS_QUERY_ARG = "DETAILS_QUERY_ARG"
        const val DETAILS_DEFAULT_QUERY_ARG = -1L
    }


}