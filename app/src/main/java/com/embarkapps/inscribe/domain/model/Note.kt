package com.embarkapps.inscribe.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "index")
    val index: Long = 0L
)