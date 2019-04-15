package com.example.peter.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title:String,
    val price:String,
    val priority: Int,
    val quanType: Int,
    val quantity:Int
)