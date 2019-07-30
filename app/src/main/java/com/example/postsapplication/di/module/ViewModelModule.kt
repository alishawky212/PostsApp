package com.example.postsapplication.di.module

import androidx.lifecycle.ViewModel
import com.example.postsapplication.detail.viewModel.DetailViewModel
import com.example.postsapplication.di.ViewModelKey
import com.example.postsapplication.master.viewModel.MasterViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MasterViewModel::class)
    internal abstract fun bindMasterViewModel(masterViewModel: MasterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}