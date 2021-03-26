package com.planatech.brightskiestask.utils.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.planatech.brightskiestask.R

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ShapeableImageView, imageUrl: String?) {
    Glide.with(imageView.context).load(imageUrl).apply(
        RequestOptions().placeholder(R.drawable.ic_placeholder_svg)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    ).into(imageView)
}