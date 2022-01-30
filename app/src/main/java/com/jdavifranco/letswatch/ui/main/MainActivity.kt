package com.jdavifranco.letswatch.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.ui.utils.Error
import com.jdavifranco.letswatch.ui.utils.Loading
import com.jdavifranco.letswatch.ui.utils.ResponseState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()

    private lateinit var tabPagerAdapter:TabPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var btnReload:Button

    private lateinit var loadingStateView:View
    private lateinit var errorStateView:View
    private lateinit var successStateView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0F

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tab_layout)
        btnReload = findViewById(R.id.btnReload)

        loadingStateView = findViewById(R.id.loadingStateView)
        errorStateView = findViewById(R.id.errorStateView)
        successStateView = findViewById(R.id.successStateView)

        viewModel.responseState.observe(this, {
            when(it){
                is Loading ->{
                    onLoading()
                }
                is Error ->{
                    onError()
                }
                is Success -> {
                    onSuccess(it.genreList)
                }
            }
        })


    }

    fun onLoading(){
        errorStateView.visibility = View.GONE
        successStateView.visibility = View.GONE
        loadingStateView.visibility = View.VISIBLE
    }

    fun onSuccess(genreList:List<Genre>){
        errorStateView.visibility = View.GONE
        successStateView.visibility = View.VISIBLE
        loadingStateView.visibility = View.GONE

        tabPagerAdapter = TabPagerAdapter(this, genreList)
        viewPager.adapter = tabPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = genreList[position].name
        }.attach()
    }

    fun onError(){
        errorStateView.visibility = View.VISIBLE
        successStateView.visibility = View.GONE
        loadingStateView.visibility = View.GONE

        btnReload.setOnClickListener {
            viewModel.fetchGenreList()
        }
    }

}