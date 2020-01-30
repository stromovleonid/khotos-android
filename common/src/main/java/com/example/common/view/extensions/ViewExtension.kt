package com.example.common.view.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun View.clicks() = callbackFlow {
    setOnClickListener { offer(Unit) }
    awaitClose { setOnClickListener(null) }
}

@ExperimentalCoroutinesApi
fun TextView.textChanges() = callbackFlow {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable) { offer(s.toString()) }
    }
    addTextChangedListener(textWatcher)

    awaitClose { removeTextChangedListener(textWatcher) }
}

fun TextInputLayout.disableError() {
    error = null
    isErrorEnabled = false
}