package com.jdavifranco.letswatch.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.ui.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()
    private lateinit var tabPagerAdapter:TabPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0F
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        viewModel.genres.observe(this, Observer {
            tabPagerAdapter = TabPagerAdapter(this, it)
            viewPager.adapter = tabPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
               tab.text = it.get(position).name
            }.attach()
        })

    }

}