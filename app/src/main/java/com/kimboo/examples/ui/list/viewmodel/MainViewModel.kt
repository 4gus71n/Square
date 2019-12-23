package com.kimboo.examples.ui.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetSquareBookmarkRepositoriesInteractor
import com.kimboo.core.interactors.GetSquareRepositoriesInteractor
import com.kimboo.core.models.SquareRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSquareRepositoriesInteractor: GetSquareRepositoriesInteractor,
    private val getSquareBookmarkRepositoriesInteractor: GetSquareBookmarkRepositoriesInteractor
) : ViewModel(), GetSquareRepositoriesInteractor.Callback,
    GetSquareBookmarkRepositoriesInteractor.Callback{

    // region Sealed classes declaration
    sealed class State {
        data class Success(
            val list: List<SquareRepository>
        ) : State()
        data class FetchedBookmarks(
            val bookmarks: List<String>
        ) : State()
        object Error : State()
    }
    // endregion

    // region Variables declaration
    val isLoading = MutableLiveData<Boolean>()
    val state = MutableLiveData<State>()
    // endregion

    // region GetSquareBookmarkRepositoriesInteractor.Callback implementation
    override fun onSuccessfullyFetchedBookmarkedRepositoriesIds(list: List<String>) {
        state.value = State.FetchedBookmarks(
            bookmarks = list
        )
    }

    override fun onErrorFetchingBookmarkedRepositories() {
        // TODO Show error message when we cannot fetch the bookmarked repositories
    }
    // endregion

    // region GetSquareRepositoriesInteractor.Callback implementation
    override fun onSquareRepositoriesSuccessfullyFetched(repositories: List<SquareRepository>) {
        isLoading.value = false
        state.value = State.Success(
            list = repositories
        )
    }

    override fun onErrorFetchingSquareRepositories() {
        isLoading.value = false
        state.value = State.Error
    }
    // endregion

    fun getBookmarks() {
        getSquareBookmarkRepositoriesInteractor.execute(this)
    }

    fun fetchRepositories() {
        isLoading.value = true
        getSquareRepositoriesInteractor.execute(this)
        getBookmarks()
    }
}