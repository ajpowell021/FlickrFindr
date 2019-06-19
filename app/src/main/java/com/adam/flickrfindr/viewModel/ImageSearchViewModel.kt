package com.adam.flickrfindr.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adam.flickrfindr.SearchImages
import com.adam.flickrfindr.model.Photo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ImageSearchViewModel @Inject constructor(private val searchImages: SearchImages) : ViewModel() {

    private val disposables = CompositeDisposable()

    val photoPage: MutableLiveData<List<Photo>> by lazy {
        MutableLiveData<List<Photo>>()
    }

    fun search(query: String) {
        searchImages.execute(SearchImages.Request(query))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                photoPage.value = response.photos.photo.orEmpty()
            }
            .let { disposables.add(it) }
    }
}
