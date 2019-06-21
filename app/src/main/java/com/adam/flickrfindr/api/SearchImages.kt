package com.adam.flickrfindr.api

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable
import javax.inject.Inject


open class SearchImages @Inject constructor(private val remote: RemoteFlickrDataSource) {

    open fun execute(request: Request): Observable<Response> {
        return remote.searchImages(request.query, request.page, request.perPage)
            .map { Response(it) }
    }

    class Request(val query: String, val page: Int, val perPage: Int = 25)

    class Response(val photos: Photos)
}
