package com.adam.flickrfindr.viewModel

import androidx.lifecycle.ViewModel
import com.adam.flickrfindr.FlickrService
import com.adam.flickrfindr.SearchImages
import com.adam.flickrfindr.model.Photos
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(private val searchImages: SearchImages, private val service: FlickrService) : ViewModel() {

    fun search() {

        val call : Observable<Photos> = service.search("cat")
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val test = it
                test
            }
    }
}
