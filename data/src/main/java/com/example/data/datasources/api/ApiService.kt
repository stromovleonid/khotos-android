package com.example.data.datasources.api

import com.example.data.model.dto.AuthResponse
import com.example.data.model.dto.PhotosResponse
import com.example.data.model.dto.UserMetadataResponse
import com.example.data.model.dto.UsersResponse
import dagger.Reusable
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject

interface ApiService {
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(@Field("login") login: String, @Field("password") password: String): Response<AuthResponse>

    @POST("/refresh_token")
    suspend fun refreshToken(): Response<AuthResponse>

    @GET("/photos")
    suspend fun getPhotosFeed(@Query("pageIndex") pageIndex: Int, @Query("pageSize") pageSize: Int): Response<PhotosResponse>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<UserMetadataResponse>

    @GET("/users")
    suspend fun getUsers(
        @Query("query") query: String,
        @Query("ignoreCase") ignoreCase: Boolean,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): Response<UsersResponse>

}

@Reusable
class ApiServiceAdapter @Inject constructor(private val apiService: ApiService) : AuthApi,
    PhotosFeedApi {

    override suspend fun refreshToken(token: String): Response<AuthResponse> =
        apiService.refreshToken()

    override suspend fun login(login: String, password: String): Response<AuthResponse> =
        apiService.login(login, password)

    override suspend fun getPhotosFeed(pageIndex: Int, pageSize: Int): Response<PhotosResponse> =
        apiService.getPhotosFeed(pageIndex, pageSize)
}