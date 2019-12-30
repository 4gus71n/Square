package com.kimboo.core.di.modules

import android.content.Context
import com.kimboo.core.room.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesAppDb(context: Context) = AppDb.create(context, false)

    @Singleton
    @Provides
    fun provideSqureRepositoryDao(appDb: AppDb) = appDb.squreRepositoryDao()

}