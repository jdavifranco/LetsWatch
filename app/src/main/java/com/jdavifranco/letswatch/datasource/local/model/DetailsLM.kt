package com.jdavifranco.letswatch.datasource.local.model

import androidx.room.PrimaryKey

data class DetailsLM(
    @PrimaryKey val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double,
    var overview:String,
    var genreLMS: List<GenreLM>,
    var runtime:String,
)