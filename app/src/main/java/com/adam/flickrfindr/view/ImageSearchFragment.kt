package com.adam.flickrfindr.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.flickrfindr.EndlessScrollListener
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.adam.flickrfindr.viewModel.ImageSearchViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ImageSearchFragment(
    val viewModel: ImageSearchViewModel,
    val picasso: Picasso
): DaggerFragment(), ImageItemView.Listener {

    // Overrides
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.image_search_fragment, container, false)

        // Views
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.image_recycler_view)
        val searchView = rootView.findViewById<SearchView>(R.id.search_view)

        // RecyclerView setup
        val layoutManager = LinearLayoutManager(activity)
        val controller = ImageController(this, picasso)
        val loadNextPage = {
            viewModel.loadNextPage()
            setPhotos(viewModel.searchResults.value!!, controller)
            Unit
        }
        val scrollListener = EndlessScrollListener(loadNextPage, layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = controller.adapter
        recyclerView.addOnScrollListener(scrollListener)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )

        // SearchView Listeners
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Do nothing on text change
                return false
            }
        })

        searchView.setOnCloseListener {
            viewModel.clearSearchResults()
            true
        }

        // ViewModel Observers
        val photoObserver = Observer<List<Photo>> {
            setPhotos(it, controller)
            scrollListener.setLoading(false)
            recyclerView.adapter = controller.adapter
        }

        viewModel.searchResults.observe(this, photoObserver)

        val loadedObserver = Observer<Boolean> {
            if (it) {
                scrollListener.setLoading(false)
                viewModel.setPageLoaded(false)
            }
        }

        viewModel.pageLoaded.observe(this, loadedObserver)

        return rootView
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onPhotoClicked(photo: Photo) {
        activity!!.supportFragmentManager
        .beginTransaction()
        .replace(R.id.content_layout, ImageDetailsFragment(photo, picasso))
        .addToBackStack(null)
        .commit()
    }

    // Private Functions
    private fun setPhotos(photos: List<Photo>, controller: ImageController) {
        controller.setPhotos(photos)
    }
}
