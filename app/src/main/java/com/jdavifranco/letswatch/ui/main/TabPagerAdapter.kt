package com.jdavifranco.letswatch.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jdavifranco.letswatch.domain.model.Genre
import com.jdavifranco.letswatch.ui.moviegallery.GalleryFragment

class TabPagerAdapter(
    activity: FragmentActivity,
    private val genreList:List<Genre>
    ) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun createFragment(position: Int): Fragment {
        val query = genreList[position].id.toString()
        return GalleryFragment.newInstance(query)
    }

}