package com.pysajin.blueprint.network

import android.app.Application
import com.pysajin.blueprint.dao.ItemListDao
import com.pysajin.blueprint.model.ItemList
import io.reactivex.Completable
import io.reactivex.Single

class ItemListRepository(application: Application) {
    private var itemListDao: ItemListDao

    init {
        val itemListDatabase = ItemListDatabase.getInstance(application)!!
        itemListDao = itemListDatabase.itemDao()
    }

    fun insertUserItem(item: ItemList): Completable {
        return itemListDao.insert(item)
    }

    fun getAllUserItem(): Single<Array<ItemList>> {
        return itemListDao.getUserAllItem()
    }

    fun deleteUserItem(id: Int): Completable {
        return itemListDao.deleteUserItem(id)
    }

}