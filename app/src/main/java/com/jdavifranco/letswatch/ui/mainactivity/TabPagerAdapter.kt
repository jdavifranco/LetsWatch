package com.jdavifranco.letswatch.ui.mainactivity

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jdavifranco.letswatch.database.Genre
import com.jdavifranco.letswatch.ui.gallery.GalleryFragment

class TabPagerAdapter(activity: FragmentActivity, private val listGenres:List<Genre>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return listGenres.size
    }

    override fun createFragment(position: Int): Fragment {
        val query = listGenres.get(position).id.toString()
        Log.e("Query", query)
        return GalleryFragment.newInstance(query)
    }

}