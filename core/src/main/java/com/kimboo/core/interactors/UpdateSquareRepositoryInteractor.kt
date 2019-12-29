package com.kimboo.core.interactors

import com.kimboo.core.models.SquareRepository

interface UpdateSquareRepositoryInteractor {
    interface Callback {
        fun onSquareRepositorySuccessfullyUpdated()
        fun onErrorTryingToBookmarkRepository()
    }

    fun execute(callback: Callback, squareRepository: SquareRepository)
}