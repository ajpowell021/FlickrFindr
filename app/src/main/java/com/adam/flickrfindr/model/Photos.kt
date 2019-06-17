package com.adam.flickrfindr.model

data class Photos (
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String?,
    val photo: List<Photo>?,
    val stat: String
)
