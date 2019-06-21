package com.adam.flickrfindr.api

import com.adam.flickrfindr.model.SearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrService {

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun search(
        @Query("api_key") apiKey: String,
        @Query("text") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Flowable<SearchResponse>
}
