package com.adam.flickrfindr.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ImageDetailsFragment(private val photo: Photo, private val picasso: Picasso) : DaggerFragment() {

    // Overrides
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.image_details_fragment, container, false)

        val imageView = rootView.findViewById<ImageView>(R.id.image_view)
        val titleView = rootView.findViewById<TextView>(R.id.title_text_view)

        picasso
            .load(photo.getLargeUrl())
            .into(imageView)

        titleView.text = photo.title

        return rootView
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}
