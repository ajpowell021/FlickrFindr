package com.adam.flickrfindr

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable
import javax.inject.Inject


class SearchImages @Inject constructor(private val remote: RemoteFlickrDataSource) {

    fun execute(request: Request): Observable<Response> {
        return remote.searchImages(request.query, request.perPage)
            .map { Response(it) }
    }

    class Request(val query: String, val perPage: Int = 25)

    class Response(val photos: Photos)
}
