package com.kimboo.core.interactors

import com.kimboo.core.repositories.SquareBookmarkRepository

class SquareBookmarkRepositoriesInteractorImpl(
    private val squareBookmarkRepository: SquareBookmarkRepository
) : SquareBookmarkRepositoriesInteractor {
    override fun execute(
        callback: SquareBookmarkRepositoriesInteractor.Callback,
        squareRepositoryId: String
    ) {
        squareBookmarkRepository.bookmarkSquareRepositoryById(squareRepositoryId)
            .subscribe(
                {
                    if (it.response) {
                        callback.onSquareRepositoryAddedIntoTheBookmarks()
                    } else {
                        callback.onSquareRepositoryRemovedFromTheBookmarks()
                    }
                },
                {
                    callback.onErrorTryingToBookmarkRepository()
                }
            )
    }
}