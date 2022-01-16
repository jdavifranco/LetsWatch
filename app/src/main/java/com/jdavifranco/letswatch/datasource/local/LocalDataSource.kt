package com.jdavifranco.letswatch.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jdavifranco.letswatch.datasource.local.model.GenreLM

@Database(entities = [MovieLM::class, GenreLM::class], version = 1, exportSchema = false)
abstract class LocalDataSource: RoomDatabase(){
    abstract val localDao:LocalDao

    companion object{
        @Volatile
        private var INSTANCE:LocalDataSource? = null

        fun getInstance(context: Context):LocalDataSource{
            var instance = INSTANCE
            if(instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDataSource::class.java,
                    "movies_database"
                    ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }

            return instance
        }
    }
}