package com.example.postsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}
inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun ImageView.loadAvatar(email: String) = Glide.with(this).load("https://api.adorable.io/avatars/285/$email").apply(
    RequestOptions.circleCropTransform()).into(this)

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}