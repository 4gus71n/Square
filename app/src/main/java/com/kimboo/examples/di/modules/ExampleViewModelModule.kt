package com.kimboo.examples.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.core.utils.ViewModelKey
import com.kimboo.examples.ui.detail.viewmodel.DetailViewModel
import com.kimboo.examples.ui.list.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ExampleViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(
        mainViewModel: MainViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(
        detailViewModel: DetailViewModel
    ): ViewModel
}