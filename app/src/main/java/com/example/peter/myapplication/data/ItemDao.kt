package com.example.peter.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM items_table")
    fun deleteAll()

    @Query("SELECT * FROM items_table ORDER BY priority DESC")
    fun getAllItems(): LiveData<List<Item>>

}