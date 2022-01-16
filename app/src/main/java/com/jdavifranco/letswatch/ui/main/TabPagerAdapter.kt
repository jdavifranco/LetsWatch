package com.jdavifranco.letswatch.views.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jdavifranco.letswatch.datasource.local.model.Genre
import com.jdavifranco.letswatch.views.moviegallery.GalleryFragment

class TabPagerAdapter(activity: FragmentActivity, private val listGenres:List<Genre>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return listGenres.size
    }

    override fun createFragment(position: Int): Fragment {
        val query = listGenres[position].id.toString()
        return GalleryFragment.newInstance(query)
    }

}