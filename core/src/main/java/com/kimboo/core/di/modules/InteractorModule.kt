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
        return GetSquareRepositoriesInteractorFlowableImpl(
            squareReposRepository = squareReposRepository,
            squareBookmarkCacheRepository = squareBookmarkRepository
        )
    }

    @Provides
    fun provideUpdateSquareRepositoryInteractor(
        squareBookmarkRepository: SquareBookmarkRepository
    ) : UpdateSquareRepositoryInteractor {
        return UpdateSquareRepositoryInteractorImpl(
            squareBookmarkRepository = squareBookmarkRepository
        )
    }

    @Provides
    fun provideGetSquareRepositoryInteractor(
        squareBookmarkRepository: SquareBookmarkRepository
    ) : GetSquareRepositoryInteractor {
        return GetSquareRepositoryInteractorImpl(
            squareBookmarkRepository = squareBookmarkRepository
        )
    }
}