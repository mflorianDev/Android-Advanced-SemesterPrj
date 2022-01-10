package com.example.natureobserverv2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Observation::class], version = 1, exportSchema = true)
abstract class ObservationDatabase: RoomDatabase() {

    abstract fun observationDAO(): ObservationDAO

    companion object{
        @Volatile
        private var INSTANCE: ObservationDatabase? = null

        // create an instance of the Database as Singleton
        fun getDatabase(context: Context): ObservationDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            // synchronized block to prevent concurrent execution from multiple threads
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ObservationDatabase::class.java,
                    "observationDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}