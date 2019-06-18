package com.adam.flickrfindr

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun appContext(app: App): Context = app

//    @Provides
//    @Singleton
//    fun getRemoteFlickrDataSource(): RemoteFlickrDataSource {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.flickr.com/")
//            .client(httpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(FlickrService::class.java)
//
//        return RetrofitFlickrDataSource(
//            retrofit
//        )
//    }

    @Provides
    fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getFlickrService(retrofit: Retrofit): FlickrService {
        return retrofit.create(FlickrService::class.java)
    }

    @Provides
    fun getRemoteFlickrDataSource(service: FlickrService): RemoteFlickrDataSource {
        return RetrofitFlickrDataSource(service)
    }

    @Provides
    fun getPicasso(context: Context): Picasso {
        return Picasso.with(context)
    }
}
