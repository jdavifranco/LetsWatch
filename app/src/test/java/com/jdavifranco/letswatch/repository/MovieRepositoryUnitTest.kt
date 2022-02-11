package com.jdavifranco.letswatch.repository

import com.jdavifranco.letswatch.datasource.mappers.toDomain
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import com.jdavifranco.letswatch.datasource.remote.model.*
import com.jdavifranco.letswatch.domain.model.RemoteConnectionException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MovieRepositoryUnitTest {

    private val testQuery = "test"
    private val testPageIndex= 1

    @Test
    fun `When getGenreList completes successfully then return a List of Genre`()= runBlocking{
        val remoteGenreList = listOf(GenreRM(id = 1, name = "action"))
        val remoteDataSource = mockk<RemoteDataSource>()

        every{ remoteDataSource.getGenreList()
        } returns GenreListRM(genres = remoteGenreList)

        val repository = MovieRepository(remoteDataSource)
        val domainGenreList = repository.getGenreList()

        verify {remoteDataSource.getGenreList()}
        assertEquals(expected = remoteGenreList.toDomain(), actual = domainGenreList)
    }

    @Test
    fun `When getGenreList fails then throw an RemoteConnectionException`(): Unit = runBlocking {
        val remoteDataSource = mockk<RemoteDataSource>()

        every { remoteDataSource.getGenreList() } throws  Exception()

        val repository = MovieRepository(remoteDataSource)
        assertFailsWith(exceptionClass = RemoteConnectionException::class,
            block = {
                repository.getGenreList()
            }
        )
        verify { remoteDataSource.getGenreList()}
    }


    @Test
    fun `When getMovieListByGenre completes successfully then return a List of Movie`(): Unit = runBlocking {
        val remoteDataSource = mockk<RemoteDataSource>()
        val remoteMovieList = MovieListRM(
            movieList = listOf(MovieRM(id = 1, title = "The Teste", posterUrl = "", releaseDate = "", vote = 10.0,)
            )
        )

        every { remoteDataSource.getMoviesByGenre(testQuery, testPageIndex) } returns remoteMovieList

        val repository = MovieRepository(remoteDataSource)
        val domainMovieList = repository.getMovieListByGenre(testQuery, testPageIndex)

        verify { remoteDataSource.getMoviesByGenre(testQuery, testPageIndex)}
        assertEquals(expected = remoteMovieList.toDomain(), actual = domainMovieList)
    }


    @Test
    fun `When getMovieListByGenre fails then throw an RemoteConnectionException`(): Unit = runBlocking {
        val remoteDataSource = mockk<RemoteDataSource>()

        every { remoteDataSource.getMoviesByGenre(testQuery, testPageIndex) } throws  Exception()

        val repository = MovieRepository(remoteDataSource)
        assertFailsWith(exceptionClass = RemoteConnectionException::class,
            block = {
                repository.getMovieListByGenre(testQuery, testPageIndex)
            }
        )
        verify { remoteDataSource.getMoviesByGenre(testQuery, testPageIndex)}
    }

    @Test
    fun `When getMovieDetails completes successfully then return a Details object`(): Unit = runBlocking {
        val remoteDataSource = mockk<RemoteDataSource>()
        val remoteDetails = DetailsRM(
            id = 1,
            title = "The Teste",
            posterUrl = "",
            releaseDate = "",
            voteAverage = 10.0,
            overview = "",
            genres = listOf(GenreRM(id = 1, name = "action")),
            runtime = "",
        )

        every { remoteDataSource.getMovieDetails(1) } returns remoteDetails

        val repository = MovieRepository(remoteDataSource)
        val domainDetails = repository.getMovieDetails(1)

        verify { remoteDataSource.getMovieDetails(1)}
        assertEquals(expected = remoteDetails.toDomain(), actual = domainDetails)
    }


    @Test
    fun `When getMovieDetails fails then throw an RemoteConnectionException`(): Unit = runBlocking {
        val remoteDataSource = mockk<RemoteDataSource>()

        every { remoteDataSource.getMovieDetails(1) } throws  Exception()

        val repository = MovieRepository(remoteDataSource)
        assertFailsWith(exceptionClass = RemoteConnectionException::class,
            block = {
                repository.getMovieDetails(1)
            }
        )
        verify {  remoteDataSource.getMovieDetails(1)}
    }


}