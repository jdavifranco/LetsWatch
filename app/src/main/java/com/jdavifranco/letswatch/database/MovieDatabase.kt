package com.jdavifranco.letswatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Movie::class, Genre::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase(){
    abstract val movieDao:MovieDao

    companion object{
        @Volatile
        private var INSTANCE:MovieDatabase? = null

        fun getInstance(context: Context):MovieDatabase{
            var instance = INSTANCE
            if(instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movies_database"
                    ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }

            return instance
        }
    }
}