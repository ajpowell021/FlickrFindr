package com.adam.flickrfindr.api

import com.adam.flickrfindr.BuildConfig
import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable
import java.lang.Exception


class RetrofitFlickrDataSource(private val service: FlickrService) :
    RemoteFlickrDataSource {

    override fun searchImages(query: String, page: Int, perPage: Int): Observable<Photos> {
        return service.search(BuildConfig.API_KEY, query, page, perPage)
            .map {
                if (it.stat != "ok") {
                    throw Exception()
                }
                else {
                    Photos(it.photos.page, it.photos.pages, it.photos.perpage, it.photos.total, it.photos.photo)
                }
            }
    }
}
