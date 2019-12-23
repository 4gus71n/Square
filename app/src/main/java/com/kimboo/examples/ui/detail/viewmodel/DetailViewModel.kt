package com.kimboo.examples.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetSquareBookmarkRepositoriesInteractor
import com.kimboo.core.interactors.SquareBookmarkRepositoriesInteractor
import com.kimboo.core.models.SquareRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val bookmarkRepositoriesInteractor: SquareBookmarkRepositoriesInteractor,
    private val getSquareBookmarkRepositoriesInteractor: GetSquareBookmarkRepositoriesInteractor
) : ViewModel(), SquareBookmarkRepositoriesInteractor.Callback,
    GetSquareBookmarkRepositoriesInteractor.Callback {

    // region Variables declaration
    sealed class StateMessage {
        object UnknownSaveBookmarkError: StateMessage()
        object UnknownFetchBookmarkError: StateMessage()
    }

    val isBookmarked = MutableLiveData<Boolean>()
    val message = MutableLiveData<StateMessage>()

    private lateinit var squareRepository: SquareRepository
    // endregion

    // region GetSquareBookmarkRepositoriesInteractor.Callback
    override fun onSuccessfullyFetchedBookmarkedRepositoriesIds(list: List<String>) {
        isBookmarked.value = list.contains(squareRepository.id)
    }

    override fun onErrorFetchingBookmarkedRepositories() {
        message.value = StateMessage.UnknownFetchBookmarkError
    }
    // endregion

    // region SquareBookmarkRepositoriesInteractor.Callback
    override fun onSquareRepositoryAddedIntoTheBookmarks() {
        isBookmarked.value = true
    }

    override fun onSquareRepositoryRemovedFromTheBookmarks() {
        isBookmarked.value = false
    }

    override fun onErrorTryingToBookmarkRepository() {
        message.value = StateMessage.UnknownSaveBookmarkError
    }
    // endregion

    fun bookmarkRepository(squareRepository: SquareRepository) {
        bookmarkRepositoriesInteractor.execute(this, squareRepository.id)
    }

    fun fetchBookmarkValue(squareRepository: SquareRepository) {
        this.squareRepository = squareRepository
        getSquareBookmarkRepositoriesInteractor.execute(this)
    }
}