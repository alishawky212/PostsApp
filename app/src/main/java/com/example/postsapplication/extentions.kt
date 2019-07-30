package com.example.postsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
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
//fun toPinnedMessageList(chatRoomId: String,activity: AppCompatActivity) {
//    activity.addFragmentBackStack("PinnedMessages", R.id.fragment_container) {
//        com.itgsolutions.eduwave.chatComponent.pinnedmessages.ui.newInstance(chatRoomId)
//    }
//}
//
//fun AppCompatActivity.addFragmentBackStack(
//    tag: String,
//    layoutId: Int,
//    newInstance: () -> Fragment
//) {
//    val fragment = supportFragmentManager.findFragmentByTag(tag) ?: newInstance()
//    supportFragmentManager.beginTransaction()
//        .setCustomAnimations(
//            R.anim.enter_from_right, R.anim.exit_to_left,
//            R.anim.enter_from_left, R.anim.exit_to_right
//        )
//        .replace(layoutId, fragment, tag)
//        .addToBackStack(tag)
//        .commit()
//}