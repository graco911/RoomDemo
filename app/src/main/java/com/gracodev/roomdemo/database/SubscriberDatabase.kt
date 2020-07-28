package com.gracodev.roomdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gracodev.roomdemo.dao.SubscriberDAO
import com.gracodev.roomdemo.entities.Subscriber

//List of entities classes and the version number of database
@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    //Abstract references from DAO´s Objects
    abstract val subscriberDAO : SubscriberDAO

    //Boilerplate Code for create instance database
    companion object{
        @Volatile
        private var INSTANCE : SubscriberDatabase? = null
        fun getInstance(context : Context) : SubscriberDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                }

                return instance
            }
        }
    }
}