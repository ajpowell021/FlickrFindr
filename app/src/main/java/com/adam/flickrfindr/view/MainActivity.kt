package com.adam.flickrfindr.view

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.adam.flickrfindr.R
import com.adam.flickrfindr.viewModel.ImageSearchViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), LifecycleOwner {

    @Inject
    lateinit var viewModel: ImageSearchViewModel

    @Inject
    lateinit var picasso: Picasso

    private lateinit var lifeCycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifeCycleRegistry = LifecycleRegistry(this)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)

        this.supportFragmentManager
        .beginTransaction()
        .replace(R.id.content_layout, ImageSearchFragment(viewModel, picasso), imageSearchTag)
        .commit()
    }

    override fun getLifecycle(): Lifecycle {
        return lifeCycleRegistry
    }

    override fun onBackPressed() {
        val searchFragment = this.supportFragmentManager.findFragmentByTag(imageSearchTag)
        if (searchFragment != null) {
            if (searchFragment.isAdded && !viewModel.searchResults.value.isNullOrEmpty()) {
                viewModel.clearSearchResults()
                return
            }
        }
        super.onBackPressed()
    }

    companion object {
        const val imageSearchTag = "IMAGE_SEARCH_FRAGMENT"
    }
}
