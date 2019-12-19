package com.kimboo.examples.di.component

import com.kimboo.core.di.component.BaseSubComponent
import com.kimboo.examples.di.modules.ExampleViewModelModule
import com.kimboo.examples.ui.detail.DetailActivity
import com.kimboo.examples.ui.list.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [ExampleViewModelModule::class]
)
interface ExampleViewInjector {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): ExampleViewInjector
    }
}