package com.adam.flickrfindr

import com.adam.flickrfindr.model.PhotoPage
import io.reactivex.Observable
import javax.inject.Inject


class SearchImages @Inject constructor(private val remote: RemoteFlickrDataSource) {

    fun execute(request: Request): Observable<Response> {
        return remote.searchImages(request.query)
            .map(::Response)
    }

    class Request(val query: String)

    class Response(val photoPage: PhotoPage)
}
