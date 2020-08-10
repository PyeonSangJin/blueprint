package com.pysajin.blueprint.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pysajin.blueprint.dao.ItemListDao
import com.pysajin.blueprint.model.ItemList

@Database(entities = [ItemList::class], version = 1, exportSchema = false)
abstract class ItemListDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemListDao

    companion object {

        private var instance: ItemListDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ItemListDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    ItemListDatabase::class.java, "itemList_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}