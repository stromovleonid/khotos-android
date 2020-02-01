package com.example.data.model

sealed class CheckTokenResult {
    object Prolonged: CheckTokenResult()
    object NoTokenSaved: CheckTokenResult()
    class Error(val exception: Exception): CheckTokenResult()
}