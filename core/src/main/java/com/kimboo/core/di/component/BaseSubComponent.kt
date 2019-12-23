package com.kimboo.core.di.component

import com.kimboo.core.interactors.GetSquareBookmarkRepositoriesInteractor
import com.kimboo.core.interactors.GetSquareRepositoriesInteractor
import com.kimboo.core.interactors.SquareBookmarkRepositoriesInteractor
import dagger.Subcomponent

/**
 * Exposes all the dependencies to the feature modules. We are supposed to expose only the interactors
 * here. We don't want the feature modules to know nothing about repositories, or any other unnecessary
 * dependency.
 */
@Subcomponent
interface BaseSubComponent {
    val getSquareBookmarkRepositoriesInteractor: GetSquareBookmarkRepositoriesInteractor
    val getSquareRepositoriesInteractor: GetSquareRepositoriesInteractor
    val bookmarkRepositoriesInteractor: SquareBookmarkRepositoriesInteractor

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}