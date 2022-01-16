package com.jdavifranco.letswatch.datasource.local

import androidx.room.*
import com.jdavifranco.letswatch.datasource.local.model.GenreLM

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenres(genreLM: List<GenreLM>)

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres():List<GenreLM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieLM: List<MovieLM>)

    @Update
    suspend fun update(movieLM: MovieLM)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAll()

    @Query("select * from movies_table")
    fun getAllMovies():List<MovieLM>

    @Query(value = "select * from movies_table where id= :id")
    suspend fun getMovieById(id: Long): MovieLM
}