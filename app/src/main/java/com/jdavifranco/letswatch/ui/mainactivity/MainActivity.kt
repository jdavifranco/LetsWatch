package com.jdavifranco.letswatch.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.jdavifranco.letswatch.R
import com.jdavifranco.letswatch.ui.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout:TabLayout = findViewById(R.id.tabs)
        addGenresTabs(tabLayout)
    }

    private fun addGenresTabs(tabLayout: TabLayout){
        for (item in viewModel.genres){
            tabLayout.addTab(tabLayout.newTab().setText(item.name));
        }
    }

}