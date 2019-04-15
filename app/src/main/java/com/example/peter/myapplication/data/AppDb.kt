package com.example.peter.myapplication.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDb :RoomDatabase() {
    abstract fun itemDao():ItemDao

    companion object {
        @Volatile private var instance: AppDb? =null

        fun getInstance(context: Context): AppDb{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance=it }
            }
        }
        private fun buildDatabase (context: Context):AppDb{
            return Room.databaseBuilder(
                context,
                AppDb::class.java,
                "App_Database"
            )
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        PopulateDbAsyncTask(instance).execute()
                    }
                }

                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }


    private class PopulateDbAsyncTask (db: AppDb?) : AsyncTask<Void, Void, Void>() {
        private val itemDao: ItemDao?

        init {
            itemDao = db?.itemDao()
        }

        override fun doInBackground(vararg voids: Void): Void? {
            itemDao?.insert(Item(1, "title 1", "6000", 1, 1, 3))
            itemDao?.insert(Item(2, "title 2", "6500", 1, 2, 45))
            itemDao?.insert(Item(3, "title 3", "8000", 2, 5, 56))
            itemDao?.insert(Item(4, "title 4", "90000", 3, 2, 78))
            return null
        }
    }}