package com.example.feature_profile.profile.view

import androidx.lifecycle.viewModelScope
import com.example.common.mvi.intent.IntentFactory
import com.example.common.mvi.intent.StateMapper
import com.example.common.mvi.model.Model
import com.example.common.mvi.view.BaseViewModel
import com.example.feature_profile.profile.model.ProfilePageModelState
import kotlinx.coroutines.launch

class ProfilePageViewModel(
    intentFactory: IntentFactory<ProfilePageViewEvent, ProfilePageModelState>,
    model: Model<ProfilePageModelState>,
    stateMapper: StateMapper<ProfilePageViewState, ProfilePageModelState>
): BaseViewModel<ProfilePageViewEvent, ProfilePageViewState, ProfilePageModelState>(
    intentFactory, model, stateMapper
) {
    init {
        viewModelScope.launch {
            onEvent(ProfilePageViewEvent.LoadMore)
        }
    }
}
