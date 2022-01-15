package com.jdavifranco.letswatch.database.model

import androidx.room.PrimaryKey

data class Details(
    @PrimaryKey val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double,
    var overview:String,
    var genres: List<Genre>,
    var runtime:String,
)