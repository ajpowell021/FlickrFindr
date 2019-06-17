package com.adam.flickrfindr

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable


class RetrofitFlickrDataSource(private val service: FlickrService) : RemoteFlickrDataSource {

    override fun searchImages(query: String): Observable<Photos> {
        return service.search(query)
            .map {
                Photos(it.page, it.pages, it.perpage, it.total, it.photo)
            }
    }
}
