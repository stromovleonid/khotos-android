package com.example.feature_photos.feed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.Constants
import com.example.common.di.InjectionsHolder
import com.example.common.mvi.view.BaseMviFragment
import com.example.common.view.extensions.paginationEvents
import com.example.feature_photos.R
import com.example.feature_photos.feed.di.DaggerPhotosFeedComponent
import com.example.feature_photos.feed.model.PhotosFeedModelState
import kotlinx.android.synthetic.main.photos_feed_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class PhotosFeedFragment :
    BaseMviFragment<PhotosFeedViewEvent, PhotosFeedViewState, PhotosFeedModelState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: PhotosFeedViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PhotosFeedViewModel::class.java)
    }

    private val photosAdapter = photosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPhotosFeedComponent
            .builder()
            .appComponent(InjectionsHolder.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photos_feed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        photosList.layoutManager = GridLayoutManager(
            requireContext(), 3,
            LinearLayoutManager.VERTICAL, false
        )

        photosList.adapter = photosAdapter

        super.onViewCreated(view, savedInstanceState)
    }

    override fun viewEvents(): Flow<PhotosFeedViewEvent> {
        return photosList.paginationEvents(
            photosList.layoutManager as LinearLayoutManager, Constants.PHOTOS_PAGE_SIZE
        ).map { PhotosFeedViewEvent.LoadMore }
    }

    override fun render(state: PhotosFeedViewState) {
        photosAdapter.items = state.cells
        photosAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = PhotosFeedFragment()
    }
}
