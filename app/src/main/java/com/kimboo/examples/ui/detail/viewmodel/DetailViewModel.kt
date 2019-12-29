package com.kimboo.examples.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetSquareRepositoryInteractor
import com.kimboo.core.interactors.UpdateSquareRepositoryInteractor
import com.kimboo.core.models.SquareRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val updateSquareRepositoryInteractor: UpdateSquareRepositoryInteractor,
    private val getGetSquareRepositoryInteractor: GetSquareRepositoryInteractor
) : ViewModel(), UpdateSquareRepositoryInteractor.Callback,
    GetSquareRepositoryInteractor.Callback {

    // region Variables declaration
    sealed class State {
        data class Success(
            val squareRepository: SquareRepository
        ) : State()
        object Error : State()
    }

    sealed class StateMessage {
        object UnknownSaveBookmarkError: StateMessage()
        object UnknownFetchBookmarkError: StateMessage()
    }

    private lateinit var _squareRepository: SquareRepository

    val state = MutableLiveData<State>()
    val message = MutableLiveData<StateMessage>()
    // endregion

    // region GetSquareBookmarkRepositoriesInteractor.Callback
    override fun onSuccessfullyFetchSquareRepository(squareRepository: SquareRepository) {
        _squareRepository = squareRepository
        state.value = State.Success(_squareRepository)
    }

    override fun onErrorFetchingSquareRepository() {
        message.value = StateMessage.UnknownFetchBookmarkError
    }
    // endregion

    // region UpdateSquareRepositoryInteractor.Callback
    override fun onSquareRepositorySuccessfullyUpdated() {
        // Do nothing.
    }

    override fun onErrorTryingToBookmarkRepository() {
        message.value = StateMessage.UnknownSaveBookmarkError
    }
    // endregion

    fun bookmarkRepository() {
        updateSquareRepositoryInteractor.execute(
            callback = this,
            squareRepository = _squareRepository.copy(
                isBookmarked = !_squareRepository.isBookmarked
            )
        )
    }

    fun getSquareRepository(squareRepositoryId: String) {
        getGetSquareRepositoryInteractor.execute(this, squareRepositoryId)
    }

    fun isBookmarked() = _squareRepository.isBookmarked
}