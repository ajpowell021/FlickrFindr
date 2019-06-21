package com.adam.flickrfindr.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adam.flickrfindr.api.SearchImages
import com.adam.flickrfindr.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


open class ImageSearchViewModel @Inject constructor(private val searchImages: SearchImages) : ViewModel() {

    private val disposables = CompositeDisposable()

    val searchResults: MutableLiveData<MutableList<Photo>> by lazy {
        MutableLiveData<MutableList<Photo>>()
    }

    val pageLoaded = MutableLiveData<Boolean>()

    private var query: String = ""

    private var pageCount: Int = 1

    fun search(query: String) {
        this.query = query
        searchImages.execute(SearchImages.Request(query, 1))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                searchResults.value = response.photos.photo?.toMutableList()
            }
            .let { disposables.add(it) }
    }

    fun loadNextPage() {
        pageCount += 1
        searchImages.execute(SearchImages.Request(query, pageCount))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                response.photos.photo?.forEach {
                    searchResults.value?.add(it)
                }
                setPageLoaded(true)
            }
            .let { disposables.add(it) }
    }

    fun setPageLoaded(loaded: Boolean) {
        pageLoaded.value = loaded
    }

    fun clearSearchResults() {
        searchResults.value?.clear()
        searchResults.value = searchResults.value
    }
}
