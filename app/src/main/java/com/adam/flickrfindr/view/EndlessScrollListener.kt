package com.adam.flickrfindr.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    private val listener: () -> Any,
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var evaluating = false

    fun setLoading(loading: Boolean) {
        this.loading = loading
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!loading && !evaluating) {
            evaluating = true
            val itemCount = layoutManager.itemCount
            val lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
            if (itemCount <= lastItemPosition + visibleThreshold) {
                loading = true
                listener()
            }
        }
        evaluating = false
    }

    companion object {
        private const val visibleThreshold = 15
    }
}
