package com.example.feature_profile.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.di.InjectionsHolder
import com.example.common.mvi.view.BaseMviFragment
import com.example.common.view.extensions.paginationEvents
import com.example.data.di.PhotosPageSizeQualifier
import com.example.feature_photos.feed.view.photosAdapter
import com.example.feature_profile.R
import com.example.feature_profile.profile.di.DaggerProfilePageComponent
import com.example.feature_profile.profile.model.ProfilePageModelState
import kotlinx.android.synthetic.main.profile_page_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ExperimentalCoroutinesApi
@FlowPreview
class ProfilePageFragment :
    BaseMviFragment<ProfilePageViewEvent, ProfilePageViewState, ProfilePageModelState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @JvmField
    @set:[Inject PhotosPageSizeQualifier]
    var pageSize: Int = 0

    override val viewModel: ProfilePageViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ProfilePageViewModel::class.java)
    }

    private val photosAdapter = photosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerProfilePageComponent
            .builder()
            .appComponent(InjectionsHolder.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        photosList.layoutManager = GridLayoutManager(
            requireContext(), 3,
            LinearLayoutManager.VERTICAL, false
        )

        photosList.adapter = photosAdapter

        super.onViewCreated(view, savedInstanceState)
    }

    override fun viewEvents(): Flow<ProfilePageViewEvent> {
        return photosList.paginationEvents(
            photosList.layoutManager as LinearLayoutManager, pageSize
        ).map { ProfilePageViewEvent.LoadMore }
    }

    override fun render(state: ProfilePageViewState) {
        photosAdapter.items = state.cells
        photosAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = ProfilePageFragment()
    }
}
