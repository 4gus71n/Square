package com.kimboo.core.di.modules

import com.kimboo.core.repositories.SquareReposNetworkRepository
import com.kimboo.core.repositories.SquareReposRepository
import com.kimboo.core.retrofit.api.SquareApi
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class RepositoryModule {
    @Provides
    fun provideSquareReposRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        squareApi: SquareApi
    ) : SquareReposRepository {
        return SquareReposNetworkRepository(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            retrofitApi = squareApi
        )
    }
}