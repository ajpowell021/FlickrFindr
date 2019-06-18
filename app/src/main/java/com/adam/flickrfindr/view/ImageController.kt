package com.adam.flickrfindr.view

import android.content.Context
import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso


class ImageController(
    private val context: Context
) : EpoxyController() {

    private var photos : List<Photo> = emptyList()
    private var picasso: Picasso? = null

    fun setPhotos(photos: List<Photo>, picasso: Picasso) {
        this.photos = photos
        this.picasso = picasso
        requestModelBuild()
    }

    override fun buildModels() {

        photos.forEachIndexed { index, flickrImage ->
            ImageItemView
                .Model(context, flickrImage, picasso)
                .id(index)
                .addTo(this)
        }
    }
}
