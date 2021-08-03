package com.jdavifranco.letswatch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdavifranco.letswatch.database.Genre
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.database.MovieDao
import com.jdavifranco.letswatch.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val moviesService: MoviesService, private val movieDao:MovieDao) {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies:LiveData<List<Movie>>
    get() = _movies
    private val _genres = MutableLiveData<List<Genre>>()
    val genres:LiveData<List<Genre>>
    get() = _genres

    //function to get genres if not in database
    suspend fun getMoviesGenres(){
        withContext(Dispatchers.IO) {
                    val networkGenres = moviesService.getGenresOfMovies()
                    val popular = Genre(-1, "POPULAR")
                    val mGenres:MutableList<Genre> = mutableListOf(popular)
                    mGenres.addAll(networkGenres.asDomainGenre())
                    movieDao.insertAllGenres(mGenres)
                    _genres.postValue(movieDao.getAllGenres())
        }
    }

    //function that gets the popular movies from service
    suspend fun getPopularMovies(page:Int){
        withContext(Dispatchers.IO){
            val networkMovies = moviesService.getPopularMovies(page)
            movieDao.insertAll(networkMovies.asDatabaseModel())
            _movies.postValue(movieDao.getAllMovies())
        }
    }

    fun getMoviesStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(moviesService, query) }
        ).flow
    }

    //function that gets de details of a movie
    suspend fun getDetailsOfMovie(movie:Movie){
        withContext(Dispatchers.IO){
            movie.detalhes = moviesService.getMovieDetails(movie.id).toDetalhesDomain()
            movieDao.update(movie)
            _movies.postValue(movieDao.getAllMovies())
        }
    }

}