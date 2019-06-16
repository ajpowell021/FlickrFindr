package com.adam.flickrfindr

import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable
import javax.inject.Inject


class SearchImages @Inject constructor(private val remote: RemoteFlickrDataSource) {

    fun execute(request: Request): Observable<Response> {
        val test = ""
        return remote.searchImages(request.query)
            .onErrorReturn { Photos(0, 0, 0, 0, emptyList()) }
            .map { Response(it) }
    }

    class Request(val query: String)

    class Response(val photoPage: Photos)
}
