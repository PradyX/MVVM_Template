/*
 * *
 *  * Created by prady on 6/20/22, 10:34 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/20/22, 10:34 PM
 *
 */

package com.prady.mvvm.template.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.prady.mvvm.template.R

class LoadImageExt {
    fun ImageView.loadImage(
        url: String,
        @DrawableRes placeholder: Int = R.drawable.ic_image_error
    ) {
        Glide.with(this)
            .load(url)
            .placeholder(placeholder)
            .into(this)
    }

    /* Usage:  Load image with extension
    img_profile_picture.loadImage(url)

    img_profile_picture.loadImage(url, R.drawable.custom_placeholder)
    */
}