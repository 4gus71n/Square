package com.kimboo.core.interactors

import com.kimboo.core.mappers.fromDtoToSquareRepositoryList
import com.kimboo.core.mappers.toDto
import com.kimboo.core.mappers.toSquareRepositoryList
import com.kimboo.core.models.SquareRepository
import com.kimboo.core.repositories.SquareBookmarkRepository
import com.kimboo.core.repositories.SquareReposRepository
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

class GetSquareRepositoriesInteractorFlowableImpl(
    private val squareReposRepository: SquareReposRepository,
    private val squareBookmarkCacheRepository: SquareBookmarkRepository
) : GetSquareRepositoriesInteractor {


    override fun execute(
        callback: GetSquareRepositoriesInteractor.Callback
    ) {
        // Subscribe to the Flowable query so we can listen for any changes from the DB
        squareBookmarkCacheRepository.getAllSquareRepositories()
            .map { it.response.fromDtoToSquareRepositoryList() }
            .subscribe(
                {
                    callback.onSquareRepositoriesSuccessfullyFetched(it)
                },
                {
                    callback.onErrorFetchingSquareRepositories()
                }
            )

        // Perform a call to the backend API to get the Square Repositories.
        getSquareRepositoriesFromApi(callback).subscribe()
    }

    private fun insertOrUpdateSquareRepositories(it: List<SquareRepository>): Observable<DataResponse<Boolean>> {
        return squareBookmarkCacheRepository.storeSquareRepositories(it.toDto())
    }

    private fun getSquareRepositoriesFromApi(
        callback: GetSquareRepositoriesInteractor.Callback
    ): Observable<DataResponse<Boolean>> {
        // Fetches the Square repositories from the API
        return squareReposRepository.fetchRepositories()
            // Maps the backend responses
            .map { it.response.toSquareRepositoryList() }
            // If everything goes okay, it merges the response in the database
            .flatMap {
                insertOrUpdateSquareRepositories(it)
            }
            // If something goes wrong, it returns a cached response from the DB
            .onErrorResumeNext { _:Throwable ->
                // Call the callback so we display the message anyways
                callback.onErrorFetchingSquareRepositories()
                // Return an empty observable so it doesn't crash
                Observable.just(DataResponse(false, DataResponse.Status.SUCCESS))
            }
    }

}
