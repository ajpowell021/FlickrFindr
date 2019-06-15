package com.adam.flickrfindr.model

data class PhotoPage (
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val total: Int,
    val photos: List<FlickrImage>
)
