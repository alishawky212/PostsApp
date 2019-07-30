package com.example.postsapplication.detail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.CommentsUseCase
import com.example.postsapplication.di.module.IO_SCHEDULER
import com.example.postsapplication.models.CommentItem
import com.example.postsapplication.models.PostsState
import com.example.postsapplication.models.mappers.CommentItemMapper
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class DetailViewModel @Inject constructor(private val commentsUseCase: CommentsUseCase,
                                          private val commentItemMapper: CommentItemMapper
                                          ,@Named(value = IO_SCHEDULER) private val ioScheduler: Scheduler
) :ViewModel() {

    private val commentsList:MutableLiveData<PostsState<CommentItem>> = MutableLiveData()
    private val compositeDisposable:CompositeDisposable = CompositeDisposable()
    fun getCommentsList():LiveData<PostsState<CommentItem>>{
        return commentsList
    }

    fun getComments(postId:String){
        compositeDisposable.add(
            commentsUseCase.getComments(postId)
                .doOnSubscribe { commentsList.postValue(PostsState.LoadingState) }
                .observeOn(ioScheduler)
                .subscribeOn(ioScheduler)
                .map { commentItemMapper.mapToPresentation(it) }
                .subscribe ({
                    commentsList.postValue(PostsState.DataState(it))
                }, { commentsList.postValue(PostsState.ErrorState(it.localizedMessage)) }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}