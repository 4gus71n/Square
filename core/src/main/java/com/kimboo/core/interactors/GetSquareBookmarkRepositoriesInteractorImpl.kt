package com.kimboo.core.interactors

import com.kimboo.core.repositories.SquareBookmarkRepository

class GetSquareBookmarkRepositoriesInteractorImpl(
    private val squareBookmarkRepository: SquareBookmarkRepository
) : GetSquareBookmarkRepositoriesInteractor {
    override fun execute(callback: GetSquareBookmarkRepositoriesInteractor.Callback) {
        squareBookmarkRepository.getBookmarkedSquareRepositoriesIds()
            .subscribe(
                {
                    callback.onSuccessfullyFetchedBookmarkedRepositoriesIds(it.response)
                },
                {
                    callback.onErrorFetchingBookmarkedRepositories()
                }
            )
    }
}