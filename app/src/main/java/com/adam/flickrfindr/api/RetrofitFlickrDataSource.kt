package com.adam.flickrfindr.api

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable


class RetrofitFlickrDataSource(private val service: FlickrService) :
    RemoteFlickrDataSource {

    override fun searchImages(query: String, page: Int, perPage: Int): Observable<Photos> {
        return service.search(API_KEY, query, page, perPage)
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

    companion object {
        const val API_KEY = "1508443e49213ff84d566777dc211f2a"
    }
}
