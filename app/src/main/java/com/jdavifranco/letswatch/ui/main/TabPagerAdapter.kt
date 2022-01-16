package com.jdavifranco.letswatch.views.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.views.moviegallery.GalleryFragment

class TabPagerAdapter(activity: FragmentActivity, private val genreList:List<Genre>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun createFragment(position: Int): Fragment {
        val query = genreList[position].id.toString()
        return GalleryFragment.newInstance(query)
    }

}