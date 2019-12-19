package com.kimboo.core.di.modules

import com.kimboo.core.retrofit.api.SquareApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideSquareApi(retrofit: Retrofit): SquareApi {
        return retrofit.create(SquareApi::class.java)
    }
}