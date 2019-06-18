package com.adam.flickrfindr.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyModel
import com.squareup.picasso.Picasso

class ImageItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    theme: Int = 0) : LinearLayout(context, attrs, theme) {

    class Model(
        private val context: Context,
        private val photo: Photo,
        private val picasso: Picasso?
    ) : EpoxyModel<ImageItemView>() {

        override fun getDefaultLayout() = R.layout.image_item_view

        override fun bind(view: ImageItemView) {

            val imageView = view.findViewById<ImageView>(R.id.image_view)
            val titleTextView = view.findViewById<TextView>(R.id.image_title_view)

            titleTextView.text = if (photo.title.isEmpty()) {
                "Untitled"
            }
            else {
                photo.title
            }

            if ((picasso != null)) {
                picasso
                    .load(photo.getUrl())
                    .fit()
                    .centerCrop()
                    .placeholder(
                        ColorDrawable(
                            ContextCompat.getColor(context, android.R.color.background_dark)
                        )
                    )
                    .error(ColorDrawable(
                        ContextCompat.getColor(context, android.R.color.holo_purple)
                        )
                    )
                    .into(imageView)
            }
        }
    }
}
