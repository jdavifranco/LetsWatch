package com.jdavifranco.letswatch.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey val id:Long,
    val title:String,
    val date:String,
    val voteAverage:Double,
    @Embedded var detalhes:Detalhes?
)

data class Detalhes(
    val overview:String,
    val genres:List<Int>,
    val runtime:String,
    val images:List<String>
)