package com.kimboo.core.di.modules

import com.kimboo.core.interactors.GetSquareRepositoriesInteractor
import com.kimboo.core.interactors.GetSquareRepositoriesInteractorImpl
import com.kimboo.core.repositories.SquareReposRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideGetSquareRepositoriesInteractor(
        squareReposRepository: SquareReposRepository
    ) : GetSquareRepositoriesInteractor {
        return GetSquareRepositoriesInteractorImpl(
            squareReposRepository = squareReposRepository
        )
    }
}