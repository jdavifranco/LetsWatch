package com.jdavifranco.letswatch.repository

import com.jdavifranco.letswatch.datasource.mappers.toDomain
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import com.jdavifranco.letswatch.datasource.remote.model.GenreListRM
import com.jdavifranco.letswatch.datasource.remote.model.GenreRM
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryUnitTest {
    private lateinit var remoteDataSource:RemoteDataSource

    @Before
    fun setUp(){
        remoteDataSource = mock(RemoteDataSource::class.java)
    }

    @Test
    fun `When remoteDataSource getGenreList completes successfully then return a List of Genre`()= runBlocking{
        val genreListRM =GenreListRM(genres= listOf(
            GenreRM(1, "action")
        ))

        `when`(remoteDataSource.getGenreList())
                .thenReturn(genreListRM)

        val repository = MovieRepository(remoteDataSource)
        assertEquals(expected = genreListRM.toDomain(), actual = repository.getGenreList())
    }

    @Test
    fun `When remoteDataSource getGenreList fails then throw an exception`(): Unit = runBlocking {
        val httpException = mock(HttpException::class.java)
        `when`(remoteDataSource.getGenreList())
            .thenThrow(httpException)

        val repository = MovieRepository(remoteDataSource)
        assertFailsWith(exceptionClass = HttpException::class,
            block = {
                repository.getGenreList()
            }
        )
    }


}