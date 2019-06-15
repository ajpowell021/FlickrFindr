package com.adam.flickrfindr

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class FlickRemoteDataSourceModule {

//    @Provides
//    @Singleton
//    fun getRemoteFlickrDataSource(context: Context): RemoteFlickrDataSource {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://www.flickr.com/services/rest/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient)
//            .build()
//            .create(FlickrService::class.java)
//
//        return RetrofitFlickrDataSource(
//            retrofit
//        )
//    }
}
