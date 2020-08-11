package com.pysajin.blueprint.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class ItemList (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val image: String?
)