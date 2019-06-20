package com.adam.flickrfindr.view

import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso


class ImageController(
    private val listener: ImageItemView.Listener,
    private val picasso: Picasso
) : EpoxyController() {

    private var photos : List<Photo> = emptyList()

    fun setPhotos(photos: List<Photo>) {
        this.photos = photos
        requestModelBuild()
    }

    override fun buildModels() {

        photos.forEachIndexed { index, flickrImage ->
            ImageItemView
                .Model(flickrImage, listener, picasso)
                .id(index)
                .addTo(this)
        }
    }
}
