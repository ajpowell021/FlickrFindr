package com.adam.flickrfindr.view

import android.content.Context
import com.adam.flickrfindr.model.FlickrImage
import com.airbnb.epoxy.EpoxyController


class ImageController(
    private val context: Context
) : EpoxyController() {

    private var flickrImages : List<FlickrImage> = emptyList()

    override fun buildModels() {
        flickrImages.forEachIndexed { index, flickrImage ->

        }
    }
}
