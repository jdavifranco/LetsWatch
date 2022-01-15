package com.jdavifranco.letswatch.database

import androidx.room.*
import com.jdavifranco.letswatch.database.model.Details
import com.jdavifranco.letswatch.database.model.Genre

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenres(genre: List<Genre>)

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres():List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<Movie>)

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAll()

    @Query("select * from movies_table")
    fun getAllMovies():List<Movie>

    @Query(value = "select * from movies_table where id= :id")
    suspend fun getMovieById(id: Long): Movie
}