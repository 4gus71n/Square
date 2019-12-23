package com.kimboo.core.interactors

interface SquareBookmarkRepositoriesInteractor {
    interface Callback {
        fun onSquareRepositoryAddedIntoTheBookmarks()
        fun onSquareRepositoryRemovedFromTheBookmarks()
        fun onErrorTryingToBookmarkRepository()
    }

    fun execute(callback: Callback, squareRepositoryId: String)
}