package com.example.postsapplication.di

import com.example.postsapplication.PostsApp
import com.example.postsapplication.di.builder.ActivityBuilder
import com.example.postsapplication.di.builder.FragmentBuilder
import com.example.postsapplication.di.module.*
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,NetworkModule::class,RepositoryModule::class,SchedulersModule::class, ViewModelModule::class,ActivityBuilder::class,FragmentBuilder::class])
interface ApplicationComponent {
    fun inject(app: PostsApp)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PostsApp): Builder

        fun build(): ApplicationComponent
    }
}