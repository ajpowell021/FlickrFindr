package com.adam.flickrfindr.api

import com.adam.flickrfindr.model.Photos
import io.reactivex.Flowable

interface RemoteFlickrDataSource {

    fun searchImages(query: String, page: Int, perPage: Int) : Flowable<Photos>
}
