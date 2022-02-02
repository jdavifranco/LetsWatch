package com.jdavifranco.letswatch.repository

import com.jdavifranco.letswatch.datasource.mappers.toDomain
import com.jdavifranco.letswatch.datasource.remote.RemoteDataSource
import com.jdavifranco.letswatch.datasource.remote.model.GenreListRM
import com.jdavifranco.letswatch.datasource.remote.model.GenreRM
import com.jdavifranco.letswatch.domain.model.RemoteConnectionException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MovieRepositoryUnitTest {

    @Test
    fun `When remoteDataSource getGenreList completes successfully then return a List of Genre`()= runBlocking{
        val expectedResult = listOf(GenreRM(id = 1, name = "action"))
        val remoteDataSource = mockk<RemoteDataSource>()
        every{ remoteDataSource.getGenreList()
        } returns GenreListRM(genres = expectedResult)

        val repository = MovieRepository(remoteDataSource)
        val result = repository.getGenreList()
        verify {remoteDataSource.getGenreList()}

        assertEquals(expected = expectedResult.toDomain(), actual = result)
    }

    @Test
    fun `When remoteDataSource getGenreList fails then throw an exception`(): Unit = runBlocking {
        val remoteDataSource = mockk<RemoteDataSource>()
        every { remoteDataSource.getGenreList() } throws  Exception()

        val repository = MovieRepository(remoteDataSource)

        assertFailsWith(exceptionClass = RemoteConnectionException::class,
            block = {
                repository.getGenreList()
            }
        )
        verify { (remoteDataSource.getGenreList())}
    }


}