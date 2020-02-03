package com.example.feature_profile.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.InjectionsHolder
import com.example.common.mvi.view.BaseMviFragment
import com.example.feature_profile.R
import com.example.feature_profile.profile.di.DaggerProfilePageComponent
import com.example.feature_profile.profile.model.ProfilePageModelState
import kotlinx.android.synthetic.main.profile_page_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


@ExperimentalCoroutinesApi
@FlowPreview
class ProfilePageFragment :
    BaseMviFragment<ProfilePageViewEvent, ProfilePageViewState, ProfilePageModelState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: ProfilePageViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ProfilePageViewModel::class.java)
    }

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


    override fun viewEvents(): Flow<ProfilePageViewEvent> = emptyFlow()

    override fun render(state: ProfilePageViewState) {
        username.text = state.user.username
    }

    companion object {
        fun newInstance() = ProfilePageFragment()
    }
}
