package com.jdavifranco.letswatch.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieLMDatabaseTest : TestCase(){
    private lateinit var localDataSource:LocalDataSource
    private lateinit var localDao:LocalDao

    @Before
    fun initDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        localDataSource = Room.inMemoryDatabaseBuilder(context, LocalDataSource::class.java).build()
        localDao = localDataSource.localDao
        super.setUp()
    }

    @After
    fun closeMovieDb(){
        localDataSource.close()
    }

    @Test
    fun getAllMovies() = runBlocking{

        val listMovie = mutableListOf(MovieLM(1, "Movie 1", "", "", 1.0),
            MovieLM(2, "Movie 2", "", "", 1.0),MovieLM(3, "Movie 3", "", "", 1.0),
            MovieLM(4, "Movie 4", "", "", 1.0),)
        localDao.insertAll(listMovie)

        assertThat(localDao.getAllMovies().size==listMovie.size).isTrue()
    }


}