package com.adam.flickrfindr.model

data class FlickrImage (
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int
)
