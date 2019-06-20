package com.adam.flickrfindr

import com.adam.flickrfindr.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrService {

    @GET("services/rest/?method=flickr.photos.search&api_key=1508443e49213ff84d566777dc211f2a&nojsoncallback=1&format=json")
    fun search(
        @Query("text") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Observable<SearchResponse>
}
