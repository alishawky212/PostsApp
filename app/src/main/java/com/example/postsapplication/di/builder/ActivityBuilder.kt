package com.example.postsapplication.di.builder

import com.example.postsapplication.MainActivity
import com.example.postsapplication.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}