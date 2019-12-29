package com.kimboo.examples.ui.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetSquareRepositoriesInteractor
import com.kimboo.core.models.SquareRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSquareRepositoriesInteractor: GetSquareRepositoriesInteractor
) : ViewModel(), GetSquareRepositoriesInteractor.Callback {

    // region Sealed classes declaration
    sealed class State {
        data class Success(
            val list: List<SquareRepository>
        ) : State()
        object Error : State()
        object ErrorFetchingBookmarks : State()
    }
    // endregion

    // region Variables declaration
    val isLoading = MutableLiveData<Boolean>()
    val state = MutableLiveData<State>()
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

    fun fetchRepositories() {
        isLoading.value = true
        getSquareRepositoriesInteractor.execute(this)
    }
}