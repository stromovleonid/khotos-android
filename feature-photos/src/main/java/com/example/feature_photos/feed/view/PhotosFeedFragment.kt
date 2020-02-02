package com.example.feature_photos.feed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.InjectionsHolder
import com.example.common.mvi.view.BaseMviFragment
import com.example.feature_photos.R
import com.example.feature_photos.feed.di.DaggerPhotosFeedComponent
import com.example.feature_photos.feed.model.PhotosFeedModelState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class PhotosFeedFragment : BaseMviFragment<PhotosFeedViewEvent, PhotosFeedViewState, PhotosFeedModelState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: PhotosFeedViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PhotosFeedViewModel::class.java)
    }

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

    companion object {
        fun newInstance() = PhotosFeedFragment()
    }

    override fun viewEvents(): Flow<PhotosFeedViewEvent> {
        return flowOf(PhotosFeedViewEvent.LoadMore)
    }

    override fun render(state: PhotosFeedViewState) {
        Toast.makeText(requireContext(), "${state.cells.size}", Toast.LENGTH_SHORT).show()
    }
}
