package com.example.common.di.network

import com.example.data.datasources.api.ApiServiceAdapter
import com.example.data.datasources.api.AuthApi
import com.example.data.interactors.token.RefreshTokenUseCase
import com.example.data.interactors.token.SecurePersistentKeyValueStorage
import com.example.data.interactors.token.TokenInteractor
import com.example.data.repositories.AuthRepository
import com.example.data.utils.DispatchersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {
    @Provides
    @Singleton
    fun providesTokenInteractor(storage: SecurePersistentKeyValueStorage, refreshTokenUseCase: RefreshTokenUseCase): TokenInteractor =
        TokenInteractor(storage, refreshTokenUseCase)

    @Provides
    @Singleton
    fun providesRefreshTokenUseCase(authRepository: AuthRepository): RefreshTokenUseCase =
        RefreshTokenUseCase(authRepository)

    @Provides
    @Singleton
    fun providesAuthRepository(authApi: AuthApi, dispatchersProvider: DispatchersProvider): AuthRepository =
        AuthRepository(authApi, dispatchersProvider)

    @Provides
    @Singleton
    fun providesAuthApi(apiServiceAdapter: ApiServiceAdapter): AuthApi =
        apiServiceAdapter

}