package com.kimboo.core.repositories

import com.kimboo.core.retrofit.responses.ApiSquareRespositoryResponse
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface SquareReposRepository {
    fun fetchRepositories() : Observable<DataResponse<List<ApiSquareRespositoryResponse>>>
}