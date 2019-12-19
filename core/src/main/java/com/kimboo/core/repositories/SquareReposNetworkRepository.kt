package com.kimboo.core.repositories

import com.kimboo.core.retrofit.api.SquareApi
import com.kimboo.core.retrofit.responses.ApiSquareRespositoryResponse
import com.kimboo.core.utils.DataResponse
import com.kimboo.core.utils.toDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class SquareReposNetworkRepository (
    private val retrofitApi: SquareApi,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : SquareReposRepository {
    override fun fetchRepositories(): Observable<DataResponse<List<ApiSquareRespositoryResponse>>> {
        return retrofitApi.fetchRepositories()
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}
