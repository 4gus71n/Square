package com.kimboo.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kimboo.core.room.dao.SqureRepositoryDao
import com.kimboo.core.room.dto.DbSquareRepositoryDto

@Database(
    entities = [DbSquareRepositoryDto::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): AppDb {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            } else {
                Room.databaseBuilder(context, AppDb::class.java, "square_example.db")
            }
            return databaseBuilder
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun squreRepositoryDao(): SqureRepositoryDao

}
