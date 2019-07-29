package com.example.postsapplication.master.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.UsersPostsUseCase
import com.example.postsapplication.di.module.IO_SCHEDULER
import com.example.postsapplication.di.module.MAIN_THREAD_SCHEDULER
import com.example.postsapplication.models.PostItem
import com.example.postsapplication.models.PostsState
import com.example.postsapplication.models.mappers.PostItemMapper
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class MasterViewModel @Inject constructor(private val useCase: UsersPostsUseCase,
                                          private val mapper: PostItemMapper
                                          ,@Named(value = IO_SCHEDULER) private val ioScheduler: Scheduler,
                                          @Named(value = MAIN_THREAD_SCHEDULER)private val mainThread:Scheduler) : ViewModel() {

    private val posts = MutableLiveData<PostsState<PostItem>>()
    private val compositeDisposable = CompositeDisposable()

    fun getAllPosts(){
        compositeDisposable.add(useCase.get()
            .doOnSubscribe { posts.postValue(PostsState.LoadingState) }
            .subscribeOn(ioScheduler)
            .observeOn(ioScheduler)
            .map { mapper.mapToPresentation(it) }
            .subscribe({
                posts.postValue(PostsState.DataState(it))
            }, { posts.postValue(PostsState.ErrorState(it.localizedMessage)) }))
    }

    fun getPostsLiveData():LiveData<PostsState<PostItem>>{
        return posts
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}