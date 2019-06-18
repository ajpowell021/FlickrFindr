package com.adam.flickrfindr.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.airbnb.epoxy.EpoxyModel
import com.squareup.picasso.Picasso

class ImageItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    theme: Int = 0) : ImageView(context, attrs, theme) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val newHeightSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, newHeightSpec)
    }

    class Model(
        private val context: Context,
        private val photo: Photo,
        private val picasso: Picasso?
    ) : EpoxyModel<ImageItemView>() {

        override fun getDefaultLayout() = R.layout.image_item_view

        override fun bind(view: ImageItemView) {

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
                    .into(view)
            }
        }
    }
}
