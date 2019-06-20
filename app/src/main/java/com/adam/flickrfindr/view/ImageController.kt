package com.adam.flickrfindr.view

import android.content.Context
import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso


class ImageController(
    private val listener: ImageItemView.Listener,
    private val picasso: Picasso,
    private val context: Context
) : EpoxyController() {

    private var photos : List<Photo> = emptyList()

    fun setPhotos(photos: List<Photo>) {
        this.photos = photos
        requestModelBuild()
    }

    override fun buildModels() {

        photos.forEachIndexed { index, flickrImage ->
            ImageItemView
                .Model(flickrImage, listener, picasso, context)
                .id(index)
                .addTo(this)
        }
    }
}
