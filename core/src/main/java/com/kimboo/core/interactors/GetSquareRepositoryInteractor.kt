package com.kimboo.core.interactors

import com.kimboo.core.models.SquareRepository

interface GetSquareRepositoryInteractor {
    interface Callback {
        fun onSuccessfullyFetchSquareRepository(
            squareRepository: SquareRepository
        )
        fun onErrorFetchingSquareRepository()
    }

    fun execute(callback: Callback, squareRepositoryId: String)
}