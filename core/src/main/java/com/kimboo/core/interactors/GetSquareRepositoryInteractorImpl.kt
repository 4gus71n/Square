package com.kimboo.core.interactors

import com.kimboo.core.mappers.toSquareRepository
import com.kimboo.core.repositories.SquareBookmarkRepository

class GetSquareRepositoryInteractorImpl(
    private val squareBookmarkRepository: SquareBookmarkRepository
) : GetSquareRepositoryInteractor {

    override fun execute(
        callback: GetSquareRepositoryInteractor.Callback,
        squareRepositoryId: String
    ) {
        squareBookmarkRepository.getSquareRepositoryById(squareRepositoryId)
            .map {
                it.response.toSquareRepository()
            }
            .subscribe(
                {
                    callback.onSuccessfullyFetchSquareRepository(it)
                },
                {
                    callback.onErrorFetchingSquareRepository()
                }
            )
    }
}