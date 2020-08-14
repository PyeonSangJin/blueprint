package com.pysajin.blueprint.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_table")
data class ItemList (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val image: String?
) : Parcelable