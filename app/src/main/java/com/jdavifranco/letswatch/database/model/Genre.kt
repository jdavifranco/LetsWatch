package com.jdavifranco.letswatch.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres_table")
data class Genre(
    @PrimaryKey val id:Long,
    val name:String
)