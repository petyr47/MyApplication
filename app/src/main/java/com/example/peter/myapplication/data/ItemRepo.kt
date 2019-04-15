package com.example.peter.myapplication.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ItemRepo(application: Application) {
    private val itemDao: ItemDao
    val allItems: LiveData<List<Item>>

    init {
        val database = AppDb.getInstance(application)
        itemDao = database.itemDao()
        allItems = itemDao.getAllItems()

    }

    fun insert(item: Item) {
        InsertItemAsyncTask(itemDao).execute(item)

    }

    fun update(item: Item) {
        UpdateItemAsyncTask(itemDao).execute(item)

    }

    fun delete(item: Item) {
        DeleteItemAsyncTask(itemDao).execute(item)

    }

    fun deleteAllItems() {
        DeleteAllItemAsyncTask(itemDao).execute()

    }

    private class InsertItemAsyncTask (private val itemDao: ItemDao) :
        AsyncTask<Item, Void, Void>() {

        override fun doInBackground(vararg items: Item): Void? {
            itemDao.insert(items[0])
            return null
        }
    }

    private class UpdateItemAsyncTask (private val itemDao: ItemDao) :
        AsyncTask<Item, Void, Void>() {

        override fun doInBackground(vararg items: Item): Void? {
            itemDao.update(items[0])
            return null
        }
    }

    private class DeleteItemAsyncTask (private val itemDao: ItemDao) :
        AsyncTask<Item, Void, Void>() {

        override fun doInBackground(vararg items: Item): Void? {
            itemDao.delete(items[0])
            return null
        }
    }

    private class DeleteAllItemAsyncTask (private val itemDao: ItemDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            itemDao.deleteAll()
            return null
        }
    }


}
