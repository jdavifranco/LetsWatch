package com.jdavifranco.letswatch.domain.model


data class Details(
    val id:Long,
    val title:String,
    val poster:String,
    val date:String,
    val voteAverage:Double,
    var overview:String,
    var genreLMS: List<Genre>,
    var runtime:String,
)