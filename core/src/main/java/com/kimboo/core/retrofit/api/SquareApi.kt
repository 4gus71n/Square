package com.kimboo.core.retrofit.api

import com.kimboo.core.retrofit.responses.ApiSquareRespositoryResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit's interface API.
 */
interface SquareApi {
    @GET("/orgs/square/repos")
    fun fetchRepositories(): Single<Response<List<ApiSquareRespositoryResponse>>>
}