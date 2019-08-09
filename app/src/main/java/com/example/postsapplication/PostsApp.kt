package com.example.postsapplication

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.postsapplication.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class PostsApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}