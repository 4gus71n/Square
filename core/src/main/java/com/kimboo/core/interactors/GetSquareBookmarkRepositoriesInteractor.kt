package com.kimboo.core.interactors

interface GetSquareBookmarkRepositoriesInteractor {
    interface Callback {
        fun onSuccessfullyFetchedBookmarkedRepositoriesIds(
            list: List<String>
        )
        fun onErrorFetchingBookmarkedRepositories()
    }

    fun execute(callback: Callback)
}