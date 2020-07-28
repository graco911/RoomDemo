package com.gracodev.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracodev.roomdemo.adapter.MyRecyclerViewAdapter
import com.gracodev.roomdemo.dao.SubscriberDAO
import com.gracodev.roomdemo.database.SubscriberDatabase
import com.gracodev.roomdemo.databinding.ActivityMainBinding
import com.gracodev.roomdemo.entities.Subscriber
import com.gracodev.roomdemo.factory.SubscriberViewModelFactory
import com.gracodev.roomdemo.repository.SubscriberRepository
import com.gracodev.roomdemo.viewmodel.SubscriberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter : MyRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)

        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter({selectedItem : Subscriber -> listItemClick(selectedItem)})
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }
    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClick(subscriber: Subscriber){
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}