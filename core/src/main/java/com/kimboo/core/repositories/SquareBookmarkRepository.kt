package com.kimboo.core.repositories

import com.kimboo.core.room.dto.DbSquareRepositoryDto
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface SquareBookmarkRepository {
    /**
     * Updates the DB against the @{squareRepositoryDto} parameter.
     * @param squareRepositoryDto The DB entity that we want to update
     */
    fun updateSquareRepository(
        squareRepositoryDto: DbSquareRepositoryDto
    ): Observable<DataResponse<Boolean>>

    /**
     * Gets all the DB entities that we have on the Square Repository table. It uses @{Flowable}
     * internally.
     */
    fun getAllSquareRepositories(): Observable<DataResponse<List<DbSquareRepositoryDto>>>

    /**
     * Gets the DB entity that matches @{squareRepositoryId}. It uses @{Flowable} internally
     */
    fun getSquareRepositoryById(
        squareRepositoryId: String
    ): Observable<DataResponse<DbSquareRepositoryDto>>

    /**
     * Inserts the collection of @{DbSquareRepositoryDto} into the DB.
     */
    fun storeSquareRepositories(
        dbSquareRepositoriesDtos: List<DbSquareRepositoryDto>
    ): Observable<DataResponse<Boolean>>
}