package com.kimboo.core.room.dao

import androidx.room.*
import com.kimboo.core.room.dto.DbSquareRepositoryDto
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface SqureRepositoryDao {
    @Query("SELECT * FROM db_square_repository_dto")
    fun getAllSquareRepositories(): Flowable<List<DbSquareRepositoryDto>>

    @Query("SELECT * FROM db_square_repository_dto where id = :id")
    fun getSquareRepositoryById(id: String): Flowable<DbSquareRepositoryDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun storeSquareRepositories(
        dbSquareRepositoryDtos: List<DbSquareRepositoryDto>
    ): Single<LongArray>

    @Update
    fun updateSquareRepository(dbSquareRepositoryDto: DbSquareRepositoryDto): Single<Int>
}