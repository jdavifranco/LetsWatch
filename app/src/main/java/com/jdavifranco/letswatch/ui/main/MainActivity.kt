package com.jdavifranco.letswatch.ui.main

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.databinding.ActivityMainBinding
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.ui.utils.ResponseState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    val viewModel: MainActivityViewModel by viewModel()
    private lateinit var tabPagerAdapter:TabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.elevation = 0F

        viewModel.responseState.observe(this, { responseState ->
            binding.responseState = responseState
            when(responseState){
                is ResponseState.Loading->{}
                is ResponseState.Error->{
                    binding.errorState.findViewById<Button>(R.id.btnReload).setOnClickListener {
                        viewModel.fetchGenreList()
                    }
                }
                is ResponseState.Success->{
                    onSuccess(responseState.result)
                }
            }
        })

        setContentView(binding.root)

    }

    private fun onSuccess(genreList:List<Genre>){
        tabPagerAdapter = TabPagerAdapter(this, genreList)
        binding.viewPager.adapter = tabPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = genreList[position].name
        }.attach()
    }
}

