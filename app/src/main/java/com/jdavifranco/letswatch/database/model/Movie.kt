package com.jdavifranco.letswatch.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double
)
