package com.kimboo.core.repositories

import android.content.SharedPreferences
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

class SquareBookmarkCacheRepository (
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler,
    private val sharedPreferences: SharedPreferences
) : SquareBookmarkRepository {
    override fun bookmarkSquareRepositoryById(squareRepositoryId: String): Observable<DataResponse<Boolean>> {
        return Single.create<DataResponse<Boolean>> {
            try {
                // Get the list of the bookmarked repositories (turn it into a mutable list so we can actually change it)
                val bookmarkIds = sharedPreferences.getStringSet(ARG_SHARED_PREFS_BOOKMARKS, null)?.let {
                    it.toMutableList()
                } ?: mutableListOf()
                val containsBookmark = bookmarkIds.contains(squareRepositoryId)
                // If it's there, remove it. If it's not, add it.
                if (containsBookmark) {
                    bookmarkIds.remove(squareRepositoryId)
                } else {
                    bookmarkIds.add(squareRepositoryId)
                }
                // Save the changes on the SharedPreferences
                sharedPreferences.edit().apply {
                    putStringSet(ARG_SHARED_PREFS_BOOKMARKS, bookmarkIds.toSet())
                    commit()
                }
                // Return the result. True if it was removed, false otherwise.
                it.onSuccess(DataResponse(!containsBookmark, DataResponse.Status.CACHED_RESPONSE))
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
        .toObservable()
        .observeOn(uiScheduler)
        .subscribeOn(backgroundScheduler)
    }

    override fun getBookmarkedSquareRepositoriesIds(): Observable<DataResponse<List<String>>> {
        return Single.create<DataResponse<List<String>>> {
            try {
                val bookmarkIds = sharedPreferences.getStringSet(ARG_SHARED_PREFS_BOOKMARKS, null) ?: emptySet()
                val result = DataResponse(bookmarkIds.toList(), DataResponse.Status.CACHED_RESPONSE)
                it.onSuccess(result)
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
        .toObservable()
        .observeOn(uiScheduler)
        .subscribeOn(backgroundScheduler)
    }

    companion object {
        private const val ARG_SHARED_PREFS_BOOKMARKS = "arg_shared_prefs_bookmars"
    }
}