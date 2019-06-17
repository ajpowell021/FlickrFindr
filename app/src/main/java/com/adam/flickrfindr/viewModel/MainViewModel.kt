package com.adam.flickrfindr.viewModel

import androidx.lifecycle.ViewModel
import com.adam.flickrfindr.SearchImages
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(private val searchImages: SearchImages) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun search() {

        searchImages.execute(SearchImages.Request("cat"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.photos
            }
            .let { disposables.add(it) }
    }
}
