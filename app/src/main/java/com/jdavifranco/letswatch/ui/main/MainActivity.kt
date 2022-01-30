package com.jdavifranco.letswatch.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jdavifranco.letswatch.databinding.ActivityMainBinding
import com.jdavifranco.letswatch.databinding.MainContentBinding
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.ui.utils.ResponseState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private lateinit var contentBinding: MainContentBinding
    private lateinit var stateBinding: ActivityMainBinding
    val viewModel: MainActivityViewModel by viewModel()
    private lateinit var tabPagerAdapter:TabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(stateBinding.root)
        supportActionBar?.elevation = 0F

        contentBinding = stateBinding.successState

        viewModel.responseState.observe(this, { responseState ->
            stateBinding.responseState = responseState

            if(responseState is ResponseState.Success) {
                onSuccess(responseState.result)
            }
        })
    }

    fun onSuccess(genreList:List<Genre>){

        tabPagerAdapter = TabPagerAdapter(this, genreList)
        contentBinding.viewPager.adapter = tabPagerAdapter
        TabLayoutMediator(contentBinding.tabLayout, contentBinding.viewPager){tab, position ->
            tab.text = genreList[position].name
        }.attach()
    }
}

