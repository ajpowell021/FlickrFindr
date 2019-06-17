package com.adam.flickrfindr.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyModel

class ImageItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    theme: Int = 0) : ImageView(context, attrs, theme) {

    class Model(
        private val context: Context,
        private val photo: Photo
    ) : EpoxyModel<ImageItemView>() {

        override fun getDefaultLayout(): Int = R.layout.image_item_view

        override fun bind(view: ImageItemView) {

        }
    }
}
