/*
 * This file is part of Doodle Android.
 *
 * Doodle Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Doodle Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Doodle Android. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2019-2022 by Patrick Zedler
 */
package com.prady.mvvm.template.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Build.VERSION_CODES
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import androidx.annotation.Dimension

object SystemUiUtil {
    const val SCRIM = 0x55000000
    const val SCRIM_DARK_DIALOG = -0xf3f3f2
    const val SCRIM_LIGHT_DIALOG = -0x99999a
    fun layoutEdgeToEdge(window: Window) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            val flags =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            val decorView = window.decorView
            decorView.systemUiVisibility = decorView.systemUiVisibility or flags
        }
    }

    fun setLightNavigationBar(view: View, isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.R) {
            view.windowInsetsController!!.setSystemBarsAppearance(
                if (isLight) WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        } else if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
            var flags = view.systemUiVisibility
            flags = if (isLight) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
            view.systemUiVisibility = flags
        }
    }

    fun setLightStatusBar(view: View, isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.R) {
            view.windowInsetsController!!.setSystemBarsAppearance(
                if (isLight) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = if (isLight) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            view.systemUiVisibility = flags
        }
    }

    fun isDarkModeActive(context: Context): Boolean {
        val uiMode = context.resources.configuration.uiMode
        return uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    fun isNavigationModeGesture(context: Context): Boolean {
        val NAV_GESTURE = 2
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "config_navBarInteractionMode", "integer", "android"
        )
        val mode = if (resourceId > 0) resources.getInteger(resourceId) else 0
        return mode == NAV_GESTURE
    }

    fun isOrientationPortrait(context: Context): Boolean {
        val orientation = context.resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_PORTRAIT
    }

    // Unit conversions
    fun dpToPx(context: Context, @Dimension(unit = Dimension.DP) dp: Float): Int {
        val r = context.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics).toInt()
    }

    fun spToPx(context: Context, @Dimension(unit = Dimension.SP) sp: Float): Int {
        val r = context.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, r.displayMetrics).toInt()
    }

    // Display width
    fun getDisplayWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.systemBars()
            )
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    // Display height
    fun getDisplayHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.systemBars()
            )
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }
}