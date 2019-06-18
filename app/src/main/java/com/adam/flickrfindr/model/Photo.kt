package com.adam.flickrfindr.model

data class Photo (
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int
) {
    fun getUrl(): String {
        return "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"
    }
}
