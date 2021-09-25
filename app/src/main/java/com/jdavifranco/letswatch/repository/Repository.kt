package com.jdavifranco.letswatch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdavifranco.letswatch.database.Genre
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.database.MovieDao
import com.jdavifranco.letswatch.network.MoviesService
import com.jdavifranco.letswatch.network.asDatabaseMovieModel
import com.jdavifranco.letswatch.network.asDetalhesDomain
import com.jdavifranco.letswatch.network.asDomainGenre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val moviesService: MoviesService, private val movieDao:MovieDao) {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres:LiveData<List<Genre>>
    get() = _genres

    fun getMoviesStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(moviesService, query) }
        ).flow
    }

    //function to get genres if not in database
    suspend fun getMoviesGenres() {
        withContext(Dispatchers.IO) {
            val dbGenres = movieDao.getAllGenres()
            if(dbGenres.isEmpty()){
                val networkGenres = moviesService.getGenresOfMovies()
                val popular = Genre(-1, "POPULAR")
                val mGenres: MutableList<Genre> = mutableListOf(popular)
                mGenres.addAll(networkGenres.asDomainGenre())
                movieDao.insertAllGenres(mGenres)
                _genres.postValue(mGenres)
            }
            else{
                _genres.postValue(dbGenres)
            }
        }
    }



    suspend fun getMovieAndDetailsById(id:Long):Movie{
       val movieNetwork = moviesService.getMovieById(id)
        val movieDomain = movieNetwork.asDatabaseMovieModel()
        movieDomain.detalhes = movieNetwork.asDetalhesDomain()
        return movieDomain
    }
}