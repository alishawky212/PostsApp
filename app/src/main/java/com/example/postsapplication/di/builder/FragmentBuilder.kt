package com.example.postsapplication.di.builder

import com.example.postsapplication.detail.DetailFragment
import com.example.postsapplication.di.scope.PerFragment
import com.example.postsapplication.master.view.MasterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {
    @PerFragment
    @ContributesAndroidInjector
    fun bindMasterFragment():MasterFragment

    @PerFragment
    @ContributesAndroidInjector
    fun bindDetailFragment():DetailFragment
}