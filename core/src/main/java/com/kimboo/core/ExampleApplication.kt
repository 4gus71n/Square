package com.kimboo.core

import android.app.Application
import com.facebook.stetho.Stetho
import com.kimboo.core.di.component.AppComponent
import com.kimboo.core.di.component.DaggerAppComponent
import com.kimboo.core.di.modules.*
import com.kimboo.core.room.AppDb

class ExampleApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        onSetupSthetho()
        onSetupRoomDatabase()
        appComponent = onBuildAppComponent()
    }

    private fun onSetupSthetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun onSetupRoomDatabase() {
        AppDb.create(this, false)
    }

    private fun onBuildAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(onBuildAppModule())
            .interactorModule(onBuildInteractorModule())
            .networkModule(onBuildNetworkModule())
            .repositoryModule(onBuildRepositoryModule())
            .retrofitModule(onBuildRetrofitModule())
            .build()
    }

    private fun onBuildRetrofitModule() = RetrofitModule()

    private fun onBuildRepositoryModule() = RepositoryModule()

    private fun onBuildNetworkModule() = NetworkModule()

    private fun onBuildInteractorModule() = InteractorModule()

    private fun onBuildAppModule() = AppModule(applicationContext)
}