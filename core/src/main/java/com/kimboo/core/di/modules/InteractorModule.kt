package com.kimboo.core.di.modules

import com.kimboo.core.interactors.*
import com.kimboo.core.repositories.SquareBookmarkRepository
import com.kimboo.core.repositories.SquareReposRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideGetSquareRepositoriesInteractor(
        squareReposRepository: SquareReposRepository,
        squareBookmarkRepository: SquareBookmarkRepository
    ) : GetSquareRepositoriesInteractor {
        return GetSquareRepositoriesInteractorImpl(
            squareReposRepository = squareReposRepository
        )
    }

    @Provides
    fun provideSquareBookmarkRepositoriesInteractor(
        squareBookmarkRepository: SquareBookmarkRepository
    ) : SquareBookmarkRepositoriesInteractor {
        return SquareBookmarkRepositoriesInteractorImpl(
            squareBookmarkRepository = squareBookmarkRepository
        )
    }

    @Provides
    fun provideGetSquareBookmarkRepositoriesInteractor(
        squareBookmarkRepository: SquareBookmarkRepository
    ) : GetSquareBookmarkRepositoriesInteractor {
        return GetSquareBookmarkRepositoriesInteractorImpl(
            squareBookmarkRepository = squareBookmarkRepository
        )
    }
}