package com.jdavifranco.letswatch.domain.model


data class Movie(
    val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double
)
