package com.kimboo.core.mappers

import com.kimboo.core.models.SquareRepository
import com.kimboo.core.retrofit.responses.ApiSquareRespositoryResponse

/**
 * Classes with extended functions to turn backend responses into model classes
 */

/**
 * Turns @{List<ApiSquareRespositoryResponse>} into @{List<SquareRepository>}
 */
fun List<ApiSquareRespositoryResponse>.toSquareRepositoryList(): List<SquareRepository> {
    return map {
        it.toSquareRepository()
    }
}

/**
 * Turns @{ApiSquareRespositoryResponse} into @{SquareRepository}
 */
fun ApiSquareRespositoryResponse.toSquareRepository(): SquareRepository {
    return SquareRepository(
        name = name,
        stars = stargazersCount,
        id = id.toString()
    )
}