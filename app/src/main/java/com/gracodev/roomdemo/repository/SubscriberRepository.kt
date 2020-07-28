package com.gracodev.roomdemo.repository

import com.gracodev.roomdemo.dao.SubscriberDAO
import com.gracodev.roomdemo.entities.Subscriber

//The purpose of a repository class is to provide
// a clean API for view models to easily get and send data.
class SubscriberRepository(private val dao : SubscriberDAO) {

    var subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) : Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber : Subscriber) : Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber : Subscriber) : Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAll()
    }
}