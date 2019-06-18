package com.adam.flickrfindr.view

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.adam.flickrfindr.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import javax.inject.Inject

class MainActivity : DaggerActivity(), LifecycleOwner {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var picasso: Picasso

    private val recyclerView : RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.image_recycler_view)
    }

    private lateinit var lifeCycleRegistry: LifecycleRegistry

    private val layoutManager = GridLayoutManager(this, ROW_LENGTH)
    private val controller = ImageController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifeCycleRegistry = LifecycleRegistry(this)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = controller.adapter

        viewModel.search()

        val photoObserver = Observer<List<Photo>> {
            setController(it)
        }

        viewModel.photoPage.observe(this, photoObserver)
    }

    override fun getLifecycle(): Lifecycle {
        return lifeCycleRegistry
    }

    private fun setController(photos: List<Photo>) {
        controller.setPhotos(photos, picasso)
    }

    companion object {
        private const val ROW_LENGTH = 4
    }
}
