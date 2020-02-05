package com.example.common.view.extensions

import android.graphics.Bitmap
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.common.view.PaginationScrollListener
import com.google.android.material.textfield.TextInputLayout
import jp.wasabeef.glide.transformations.BlurTransformation
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

@ExperimentalCoroutinesApi
fun RecyclerView.paginationEvents(manager: LinearLayoutManager, pageSize: Int) = callbackFlow {
    val listener = object : PaginationScrollListener(manager, pageSize) {
        override fun loadMoreItems() {
            offer(Unit)
        }
    }
    addOnScrollListener(listener)

    awaitClose { removeOnScrollListener(listener) }
}

fun ImageView.loadAndExtractPalette(url: String?, listener: (Palette) -> Unit) =  url?.let { imageUrl ->
    Glide.with(this)
        .asBitmap()
        .load(imageUrl)
        .transform(BlurTransformation())
        .listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ) = false

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let { listener(Palette.from(it).generate()) }
                return false
            }
        })
        .into(this)
}