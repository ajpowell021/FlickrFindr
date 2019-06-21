package com.adam.flickrfindr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adam.flickrfindr.api.SearchImages
import com.adam.flickrfindr.model.Photo
import com.adam.flickrfindr.model.Photos
import com.adam.flickrfindr.viewModel.ImageSearchViewModel
import io.reactivex.Flowable
import org.junit.*
import org.mockito.Mockito.*

class ImageSearchViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private val searchImages = mock(SearchImages::class.java)
    private val viewModel = ImageSearchViewModel(searchImages)
    private val stubPhoto = Photo("1", "1", "1", "1", 1, "Stubbed Title", 0, 0, 0)
    private val stubPhotos = Photos(1, 1, 1, "1", listOf(stubPhoto))
    private val stubbedRequest = SearchImages.Request("cat", 1)
    private val stubbedResponse = Flowable.just(SearchImages.Response(stubPhotos))

    @Before
    fun setup() {
        `when`(searchImages.execute(stubbedRequest)).thenReturn(stubbedResponse)
    }

    @Test
    fun isLoadedTest() {
        // When
        viewModel.setPageLoaded(true)
        // Then
        assert(viewModel.pageLoaded.value == true)
    }
}
