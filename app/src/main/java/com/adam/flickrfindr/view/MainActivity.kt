package com.adam.flickrfindr.view

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.adam.flickrfindr.R
import com.adam.flickrfindr.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), LifecycleOwner {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var picasso: Picasso

    private lateinit var lifeCycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifeCycleRegistry = LifecycleRegistry(this)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)

        val manager = this.supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.replace(R.id.content_layout, ImageSearchFragment(viewModel, picasso))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun getLifecycle(): Lifecycle {
        return lifeCycleRegistry
    }
}
