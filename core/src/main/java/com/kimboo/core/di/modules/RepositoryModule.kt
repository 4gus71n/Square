package com.kimboo.core.di.modules

import com.kimboo.core.repositories.SquareBookmarkRepository
import com.kimboo.core.repositories.SquareBookmarkRoomRepository
import com.kimboo.core.repositories.SquareReposNetworkRepository
import com.kimboo.core.repositories.SquareReposRepository
import com.kimboo.core.retrofit.api.SquareApi
import com.kimboo.core.room.dao.SqureRepositoryDao
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

    @Provides
    fun provideSquareBookmarkRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        squreRepositoryDao: SqureRepositoryDao
    ) : SquareBookmarkRepository {
        return SquareBookmarkRoomRepository(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            squreRepositoryDao = squreRepositoryDao
        )
    }
}