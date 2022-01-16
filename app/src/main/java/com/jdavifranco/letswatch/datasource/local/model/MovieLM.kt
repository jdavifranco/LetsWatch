package com.jdavifranco.letswatch.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieLM(
    @PrimaryKey val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double
)
