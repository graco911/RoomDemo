package com.gracodev.roomdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gracodev.roomdemo.entities.Subscriber

//Data Access Object
@Dao
interface SubscriberDAO {

    //Suspend modifier
    @Insert
    suspend fun insertSubscriber(subscriber : Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber : Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber : Subscriber) : Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll() : Int

    //Don´t need use background thread for execute this function, so the suspend is not necessary
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers() : LiveData<List<Subscriber>>
}