package com.kimboo.core.interactors

import com.kimboo.core.mappers.toSquareRepositoryList
import com.kimboo.core.repositories.SquareReposRepository

class GetSquareRepositoriesInteractorImpl(
    private val squareReposRepository: SquareReposRepository
) : GetSquareRepositoriesInteractor {
    override fun execute(callback: GetSquareRepositoriesInteractor.Callback) {
        squareReposRepository.fetchRepositories()
            .map { it.response.toSquareRepositoryList() }
            .subscribe(
                {
                    callback.onSquareRepositoriesSuccessfullyFetched(it)
                },
                {
                    callback.onErrorFetchingSquareRepositories()
                }
            )
    }
}
