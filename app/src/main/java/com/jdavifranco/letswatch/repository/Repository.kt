package com.jdavifranco.letswatch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdavifranco.letswatch.database.Genre
import com.jdavifranco.letswatch.database.Movie
import com.jdavifranco.letswatch.database.MovieDao
import com.jdavifranco.letswatch.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val moviesService: MoviesService, private val movieDao:MovieDao) {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies:LiveData<List<Movie>>
    get() = _movies
    private var _genres:MutableList<Genre> = mutableListOf()
    val genres:List<Genre>
    get() = _genres

    //function to get genres if not in database
    suspend fun getMoviesGenres(){
        if(_genres.size==0) {
            _genres.addAll(movieDao.getAllGenres())
            if(_genres.size==0){
                withContext(Dispatchers.IO) {
                    val networkGenres = moviesService.getGenresOfMovies()
                    _genres.addAll(networkGenres.asDomainGenre())
                    movieDao.insertAllGenres(_genres)
                }
            }
        }
    }

    //function that gets the popular movies from service
    suspend fun getPopularMovies(){
        withContext(Dispatchers.IO){
            val networkMovies = moviesService.getPopularMovies()
            movieDao.insertAll(networkMovies.asDatabaseModel())
            _movies.postValue(movieDao.getAllMovies())
        }
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