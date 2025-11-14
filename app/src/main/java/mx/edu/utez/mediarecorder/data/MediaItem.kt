package com.ejemplo.mediaapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class MediaType { AUDIO, IMAGE, VIDEO }

@Entity(tableName = "media_items")
data class MediaItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: String,
    val name: String,
    val date: Long,
    val duration: Long,
    val type: MediaType
)
