package com.pysajin.blueprint.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pysajin.blueprint.model.ItemList
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ItemListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemList): Completable

    @Query("SELECT * FROM item_table")
    fun getUserAllItem(): Single<Array<ItemList>>

    @Query("DELETE  FROM item_table WHERE id = :id")
    fun deleteUserItem(id: Int): Completable



//    @Query("SELECT * FROM item_table WHERE id=:id")
//    fun getItem(id: Int?): Single<ItemList>
}
