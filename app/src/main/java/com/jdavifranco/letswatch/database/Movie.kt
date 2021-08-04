package com.jdavifranco.letswatch.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double,
    @Embedded var detalhes:Detalhes?
)

data class Detalhes(
    var overview:String,
    var genres:String,
    var runtime:String,
    var images:String?,
)

@Entity(tableName = "genres_table")
data class Genre(
    @PrimaryKey val id:Long,
    val name:String
)