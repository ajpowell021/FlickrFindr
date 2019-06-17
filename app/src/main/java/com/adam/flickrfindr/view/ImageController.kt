package com.adam.flickrfindr.view

import android.content.Context
import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyController


class ImageController(
    private val context: Context
) : EpoxyController() {

    private var photos : List<Photo> = emptyList()

    override fun buildModels() {
        photos.forEachIndexed { index, flickrImage ->

        }
    }
}
