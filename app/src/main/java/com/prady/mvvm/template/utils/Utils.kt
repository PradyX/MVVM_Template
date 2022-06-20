/*
 * *
 *  * Created by prady on 6/20/22, 10:27 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/20/22, 10:27 PM
 *
 */

package com.prady.mvvm.template.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Utils {

    // Checking nullability
    fun Any?.isNull() = this == null

/* Usage : Checking null with extention
    if(seller.location.isNull() || seller.destination.isNull().not()){
        //Do something here
    }
*/

    //Extension function
    fun String?.valid(): Boolean =
        this != null && !this.equals("null", true)
                && this.trim().isNotEmpty()
/* Usage at call site
   if(data.valid())
 */

    // Toast extensions
    fun Context.showShotToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    // Snack bar Extensions
    fun View.showShotSnackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
    }

    fun View.showLongSnackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }

    fun View.snackBarWithAction(
        message: String, actionlable: String,
        block: () -> Unit
    ) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            .setAction(actionlable) {
                block()
            }
    }
}