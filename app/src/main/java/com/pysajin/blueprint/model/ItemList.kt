package com.pysajin.blueprint.model

import androidx.room.Entity

@Entity(tableName = "item_table")
data class ItemList (
    val id: String,
    val title: String,
    val image: String?
)