package com.kimboo.core.interactors

import com.kimboo.core.mappers.toDto
import com.kimboo.core.models.SquareRepository
import com.kimboo.core.repositories.SquareBookmarkRepository

class UpdateSquareRepositoryInteractorImpl(
    private val squareBookmarkRepository: SquareBookmarkRepository
) : UpdateSquareRepositoryInteractor {
    override fun execute(
        callback: UpdateSquareRepositoryInteractor.Callback,
        squareRepository: SquareRepository
    ) {
        squareBookmarkRepository.updateSquareRepository(squareRepository.toDto())
            .subscribe(
                {
                    if (it.response) {
                        callback.onSquareRepositorySuccessfullyUpdated()
                    } else {
                        callback.onErrorTryingToBookmarkRepository()
                    }
                },
                {
                    callback.onErrorTryingToBookmarkRepository()
                }
            )
    }
}