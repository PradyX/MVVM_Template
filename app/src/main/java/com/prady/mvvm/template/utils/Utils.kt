/*
 * *
 *  * Created by prady on 6/20/22, 10:27 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/20/22, 10:27 PM
 *
 */

package com.prady.mvvm.template.utils

class Utils {

    // Checking nullability
    fun Any?.isNull() = this == null

/* Usage : Checking null with extention
    if(seller.location.isNull() || seller.destination.isNull().not()){
        //Do something here
    }
*/


}