package com.adam.flickrfindr

import com.adam.flickrfindr.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBindingModule {

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity
}
