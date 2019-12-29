package com.kimboo.core.mappers

import com.kimboo.core.models.SquareRepository
import com.kimboo.core.retrofit.responses.ApiSquareRespositoryResponse
import com.kimboo.core.room.dto.DbSquareRepositoryDto

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
        id = id.toString(),
        isBookmarked = false // Default value
    )
}

fun List<SquareRepository>.toDto(): List<DbSquareRepositoryDto> {
    return map {
        it.toDto()
    }
}

fun SquareRepository.toDto(): DbSquareRepositoryDto {
    return DbSquareRepositoryDto(
        id = id.toInt(),
        isBookmarked = isBookmarked,
        name = name,
        stars = stars
    )
}

fun DbSquareRepositoryDto.toSquareRepository(): SquareRepository {
    return SquareRepository(
        name = name,
        stars = stars,
        id = id.toString(),
        isBookmarked = isBookmarked
    )
}

fun List<DbSquareRepositoryDto>.fromDtoToSquareRepositoryList(): List<SquareRepository> {
    return map {
        it.toSquareRepository()
    }
}
