package com.example.data.repositories

import com.example.data.exceptions.ApiException
import com.example.data.exceptions.NetworkConnectionException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseApiRepository {

    protected suspend fun <T> executeApiRequest(request: suspend () -> Response<T>): T {
        try {
            return request().body() ?: throw ApiException()
        } catch (e: SocketTimeoutException) {
            throw NetworkConnectionException()
        } catch (e: UnknownHostException) {
            throw NetworkConnectionException()
        }
    }
}