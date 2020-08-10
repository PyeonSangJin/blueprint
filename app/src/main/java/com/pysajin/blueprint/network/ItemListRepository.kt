package com.pysajin.blueprint.network

import android.app.Application
import com.pysajin.blueprint.dao.ItemListDao
import com.pysajin.blueprint.model.ItemList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ItemListRepository(application: Application) {
    private var itemListDao: ItemListDao

    init {
        val itemListDatabase = ItemListDatabase.getInstance(application)!!
        itemListDao = itemListDatabase.itemDao()
    }

    fun insertUersItem(item: ItemList): Completable {
        return itemListDao.insert(item)
    }

    fun getAllUserItem(): Single<ArrayList<ItemList>> {
        return itemListDao.getUserAllItem()
    }

    fun deleteUserItem(id: Int): Completable {
        return itemListDao.deleteUserItem(id)
    }

}