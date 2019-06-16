package com.adam.flickrfindr

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable

interface RemoteFlickrDataSource {

    fun searchImages(query: String) : Observable<Photos>
}
