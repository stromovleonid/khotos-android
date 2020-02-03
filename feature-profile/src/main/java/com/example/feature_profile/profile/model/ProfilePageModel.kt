package com.example.feature_profile.profile.model

import com.example.common.mvi.model.BaseModel
import com.example.data.repositories.AuthRepository
import com.example.data.repositories.UsersRepository
import com.example.data.utils.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.map

@FlowPreview
@ExperimentalCoroutinesApi
class ProfilePageModel(
    initialState: ProfilePageModelState,
    private val usersRepository: UsersRepository,
    private val authRepository: AuthRepository,
    dispatchersProvider: DispatchersProvider
) : BaseModel<ProfilePageModelState>(initialState, dispatchersProvider) {

    override fun submitState(newState: ProfilePageModelState) = Unit

    override fun observe() = usersRepository.getUserWithPhotos(authRepository.getCurrentUserId()).map {
        ProfilePageModelState.Data(it)
    }
}


