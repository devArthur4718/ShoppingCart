package com.devarthur4718.mvp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devarthur4718.mvp.repository.business.Business
import com.devarthur4718.mvp.repository.business.BussinessDAO

@Database(
    entities = [Business::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase(){

    abstract fun BussinessDAO() : BussinessDAO
    companion object{
        //Singleton that prevents multiples instances of database opening at the same time.
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context : Context) : ApplicationDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "PallevarDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}