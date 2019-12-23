package com.kimboo.core.repositories

import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface SquareBookmarkRepository {
    fun bookmarkSquareRepositoryById(squareRepositoryId: String): Observable<DataResponse<Boolean>>
    fun getBookmarkedSquareRepositoriesIds(): Observable<DataResponse<List<String>>>
}