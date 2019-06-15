package com.adam.flickrfindr

import com.adam.flickrfindr.model.PhotoPage
import io.reactivex.Observable


class RetrofitFlickrDataSource constructor(private val service: FlickrService) : RemoteFlickrDataSource {

    override fun searchImages(query: String): Observable<PhotoPage> {
        return service.search(query)
            .map {
                PhotoPage(it.page, it.pages, it.perPage, it.total, it.photos)
            }
    }
}
