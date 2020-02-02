package com.example.common.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val pageSize: Int
) :
    RecyclerView.OnScrollListener() {

    var isLastPage: Boolean = false

    var isLoading: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= pageSize
            ) {
                Timber.i("on loadMore()")
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
}

