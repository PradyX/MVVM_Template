/*
 * *
 *  * Created by prady on 6/20/22, 10:43 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/20/22, 10:43 PM
 *
 */

package com.prady.mvvm.template.utils

import android.view.View

class VisibiltyExt {
    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.invisible() {
        this.visibility = View.INVISIBLE
    }

    fun View.toggleVisibility(): View {
        visibility = if (visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
        return this
    }
}