package com.example.feature_photos.feed.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.feature_photos.R
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun photoAdapterDelegate() = adapterDelegate<PhotosFeedCell.PhotoCell, PhotosFeedCell>(R.layout.item_photo) {
    val photo : ImageView = findViewById(R.id.photo)
    bind {
        Glide.with(photo)
            .load(item.url)
            .into(photo)
    }
}

fun loadingAdapterDelegate() = adapterDelegate<PhotosFeedCell.LoadingCell, PhotosFeedCell>(R.layout.item_loading) {

}

fun photosAdapter() = ListDelegationAdapter<List<PhotosFeedCell>> (
    photoAdapterDelegate(),
    loadingAdapterDelegate()
)

