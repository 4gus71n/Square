package com.kimboo.core.room.dto

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "db_square_repository_dto",
    indices = [Index(
        value = ["id"],
        unique = true
    )]
)
data class DbSquareRepositoryDto(
    @PrimaryKey
    val id: Int,
    val name: String,
    val stars: Int,
    val isBookmarked: Boolean = false
)