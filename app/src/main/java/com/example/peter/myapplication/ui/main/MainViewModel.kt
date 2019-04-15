package com.example.peter.myapplication.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.peter.myapplication.data.Item
import com.example.peter.myapplication.data.ItemRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: ItemRepo
    val allItems: LiveData<List<Item>>

    init {
        repo = ItemRepo(application)
        allItems = repo.allItems
    }

    fun insert(item: Item) {
        repo.insert(item)
    }

    fun update(item: Item) {
        repo.update(item)
    }

    fun delete(item: Item) {
        repo.delete(item)
    }

    fun deleteAllItems() {
        repo.deleteAllItems()
    }

}
