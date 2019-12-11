package com.app.mvvmdemo.LocalData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Gautam Mittal
 * 9/12/19
 */


@Database(entities = arrayOf(LoginData::class),version = 1)
abstract class DataBaseClass:RoomDatabase()
{

    abstract fun Dao():LoginDao
    companion object {
        private var INSTANCE: DataBaseClass? = null
        fun getAppDatabase(context: Context): DataBaseClass {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseClass::class.java,
                    "user-database"
                ).allowMainThreadQueries()
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .build()
            }
            return INSTANCE as DataBaseClass
        }
    }
    fun destroyInstance() {
        INSTANCE = null
    }
    /*


    abstract fun Dao(): LoginDao


    companion object
    {
        fun getInstance(context: Context): DataBaseClass
        {
            return Room.databaseBuilder(    context,DataBaseClass::class.java,"login.db").allowMainThreadQueries() .build()
        }



    }*/
}

