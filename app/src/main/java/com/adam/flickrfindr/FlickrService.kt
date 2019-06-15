package com.adam.flickrfindr

import com.adam.flickrfindr.model.PhotoPage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FlickrService {

    @GET("?method=flickr.photos.search&api_key=1508443e49213ff84d566777dc211f2a&format=json&nojsoncallback=1")
    fun search(
        @Query("text") query: String
    ) : Observable<PhotoPage>
}
