package com.adam.flickrfindr

import com.adam.flickrfindr.view.ImageDetailsFragment
import com.adam.flickrfindr.view.ImageSearchFragment
import com.adam.flickrfindr.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBindingModule {

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun getImageSearchFragment(): ImageSearchFragment

    @ContributesAndroidInjector
    abstract fun getImageDetailsFragment(): ImageDetailsFragment
}
