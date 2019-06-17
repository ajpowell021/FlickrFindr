package com.adam.flickrfindr

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable


class RetrofitFlickrDataSource(private val service: FlickrService) : RemoteFlickrDataSource {

    override fun searchImages(query: String, perPage: Int): Observable<Photos> {
        return service.search(query, perPage)
            .map {
                if (it.stat != "ok") {
                    // Something is wrong, maybe error handle this?
                    Photos(0, 0, 0, "0", emptyList())
                }
                else {
                    Photos(it.photos.page, it.photos.pages, it.photos.perpage, it.photos.total, it.photos.photo)
                }
            }
    }
}
