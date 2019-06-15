package com.adam.flickrfindr

import com.adam.flickrfindr.model.PhotoPage
import io.reactivex.Observable

interface RemoteFlickrDataSource {

    fun searchImages(query: String) : Observable<PhotoPage>
}
