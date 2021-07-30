package com.jdavifranco.letswatch.database

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movie: List<Movie>)

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAll()

    @Query("select * from movies_table")
    fun getAllMovies():List<Movie>

    @Query(value = "select * from movies_table where id= :id")
    suspend fun getFilmeById(id: Long):Movie
}