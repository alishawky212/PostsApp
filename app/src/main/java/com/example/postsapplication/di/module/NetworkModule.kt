package com.example.postsapplication.di.module

import dagger.Module
import com.example.data.remote.RemoteApis
import retrofit2.Retrofit
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): RemoteApis {
        return retrofit.create<RemoteApis>(RemoteApis::class.java)
    }
}