package com.jdavifranco.letswatch.database

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
class MovieDatabaseTest : TestCase(){
    private lateinit var movieDb:MovieDatabase
    private lateinit var movieDao:MovieDao

    @Before
    fun initDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        movieDb = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
        movieDao = movieDb.movieDao
        super.setUp()
    }

    @After
    fun closeMovieDb(){
        movieDb.close()
    }

    @Test
    fun getAllMovies() = runBlocking{

        val listMovie = mutableListOf(Movie(1, "Movie 1", "", "", 1.0, null),
            Movie(2, "Movie 2", "", "", 1.0, null),Movie(3, "Movie 3", "", "", 1.0, null),
            Movie(4, "Movie 4", "", "", 1.0, null),)
        movieDao.insertAll(listMovie)

        assertThat(movieDao.getAllMovies().size==listMovie.size).isTrue()
    }


}