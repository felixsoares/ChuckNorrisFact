package com.felix.chucknorrisfact.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "fact"
)
data class FactEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val fact: String
)
