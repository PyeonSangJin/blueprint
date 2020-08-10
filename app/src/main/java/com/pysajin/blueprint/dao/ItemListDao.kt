package com.pysajin.blueprint.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pysajin.blueprint.model.ItemList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ItemListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemList): Completable

    @Query("SELECT * FROM item_table WHERE id=:id")
    fun getItem(id: Int?): Single<ItemList>

    @Query("SELECT * FROM item_table")
    fun getUserAllItem(): Single<ArrayList<ItemList>>

    @Query("DELETE  FROM item_table WHERE id = :id")
    fun deleteUserItem(id: Int): Completable
}
