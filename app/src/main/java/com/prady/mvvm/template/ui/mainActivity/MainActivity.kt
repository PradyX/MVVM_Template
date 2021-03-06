/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:02 PM
 *
 */

package com.prady.mvvm.template.ui.mainActivity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.prady.mvvm.template.R
import com.prady.mvvm.template.databinding.ActivityMainBinding
import com.prady.mvvm.template.helpers.PreferenceManager
import com.prady.mvvm.template.ui.mainActivity.fragments.HomeFragment
import com.prady.mvvm.template.utils.ConnectionLiveData

class MainActivity : AppCompatActivity() {

    private lateinit var preferenceManager : PreferenceManager
    private lateinit var binding : ActivityMainBinding
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager.instance
        preferenceManager.userid = "123"
        Log.e("pref_test", preferenceManager.userid.toString())

//        if(savedInstanceState == null){
//            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
//        }

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                updateUI(it)
            }
        }
    }

    private fun updateUI(it: Boolean) {
        if(it){
            Log.e("Network", "Available")
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }else{
            Log.e("Network", "Not Available")

            CFAlertDialog.Builder(this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setHeaderView(R.layout.dialog_header_no_connection)
                .setTitle("No Internet Connection")
                .setMessage("Please check your internet connection")
                .addButton("Dismiss", -1, -1,
                    CFAlertDialog.CFAlertActionStyle.NEGATIVE,
                    CFAlertDialog.CFAlertActionAlignment.JUSTIFIED
                ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
                .show()

        }
    }
}