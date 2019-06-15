package com.adam.flickrfindr.viewModel

import androidx.lifecycle.ViewModel
import com.adam.flickrfindr.SearchImages
import javax.inject.Inject


class MainViewModel @Inject constructor(private val searchImages: SearchImages) : ViewModel() {

    fun search() {
        val response = searchImages.execute(SearchImages.Request("cat"))
            .map { it.photoPage }

        response.map { it }
    }
}
