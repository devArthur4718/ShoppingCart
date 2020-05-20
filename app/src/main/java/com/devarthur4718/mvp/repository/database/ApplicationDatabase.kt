package com.devarthur4718.mvp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devarthur4718.mvp.repository.business.BusinessData
import com.devarthur4718.mvp.repository.business.BussinessDAO

@Database(
    entities = [BusinessData::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase(){

    abstract fun BussinessDAO() : BussinessDAO
    companion object{
        //Singleton that prevents multiples instances of database opening at the same time.
        // gives imediate accesss to the var in the thread
        @Volatile private var instance: ApplicationDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context : Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "Pallevar.db"
                    ).build()

    }

}