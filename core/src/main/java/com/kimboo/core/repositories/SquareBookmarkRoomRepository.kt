package com.kimboo.core.repositories

import com.kimboo.core.room.dao.SqureRepositoryDao
import com.kimboo.core.room.dto.DbSquareRepositoryDto
import com.kimboo.core.utils.DataResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler

class SquareBookmarkRoomRepository (
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler,
    private val squreRepositoryDao: SqureRepositoryDao
) : SquareBookmarkRepository {
    override fun updateSquareRepository(
        squareRepositoryDto: DbSquareRepositoryDto
    ): Observable<DataResponse<Boolean>> {
        return squreRepositoryDao.updateSquareRepository(squareRepositoryDto)
            .toObservable()
            .map { DataResponse(it > 0, DataResponse.Status.CACHED_RESPONSE) }
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun getSquareRepositoryById(squareRepositoryId: String): Observable<DataResponse<DbSquareRepositoryDto>> {
        return squreRepositoryDao.getSquareRepositoryById(squareRepositoryId)
            .toObservable()
            .map { DataResponse(it, DataResponse.Status.CACHED_RESPONSE) }
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun getAllSquareRepositories(): Observable<DataResponse<List<DbSquareRepositoryDto>>> {
        return squreRepositoryDao.getAllSquareRepositories()
            .toObservable()
            .map { DataResponse(it, DataResponse.Status.CACHED_RESPONSE) }
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun storeSquareRepositories(
        dbSquareRepositoriesDtos: List<DbSquareRepositoryDto>
    ): Observable<DataResponse<Boolean>> {
        return squreRepositoryDao.storeSquareRepositories(dbSquareRepositoriesDtos)
            .map { DataResponse(it.isNotEmpty(), DataResponse.Status.CACHED_RESPONSE) }
            .toObservable()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}