package com.adam.flickrfindr.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.flickrfindr.R
import com.adam.flickrfindr.model.Photo
import com.adam.flickrfindr.viewModel.ImageSearchViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ImageSearchFragment @Inject constructor(val viewModel: ImageSearchViewModel, val picasso: Picasso): DaggerFragment(), ImageItemView.Listener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.image_search_fragment, container, false)

        val layoutManager = LinearLayoutManager(activity)
        val controller = ImageController(requireContext(), this)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.image_recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = controller.adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )

        val searchView = rootView.findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchView.setOnCloseListener {
            viewModel.clearSearchResults()
            true
        }

        val photoObserver = Observer<List<Photo>> {
            controller.setPhotos(it, picasso)
            recyclerView.adapter = controller.adapter
        }

        viewModel.searchResults.observe(this, photoObserver)

        return rootView
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onPhotoClicked(photo: Photo) {
        val manager = activity!!.supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.content_layout, ImageDetailsFragment(photo))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
