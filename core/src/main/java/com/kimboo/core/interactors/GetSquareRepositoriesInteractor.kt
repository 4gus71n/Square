package com.kimboo.core.interactors

import com.kimboo.core.models.SquareRepository

interface GetSquareRepositoriesInteractor {
    interface Callback {
        fun onSquareRepositoriesSuccessfullyFetched(
            repositories: List<SquareRepository>
        )
        fun onErrorFetchingSquareRepositories()
    }
    fun execute(callback: Callback)
}