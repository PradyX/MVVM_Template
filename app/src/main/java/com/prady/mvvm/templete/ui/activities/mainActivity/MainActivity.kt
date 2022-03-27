package com.prady.mvvm.templete.ui.activities.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prady.mvvm.templete.R
import com.prady.mvvm.templete.databinding.ActivityMainBinding
import com.prady.mvvm.templete.ui.activities.mainActivity.fragments.HomeFragment
import com.prady.mvvm.templete.utils.ConnectionLiveData
import com.prady.srmgpc_user.helpers.PreferenceManager

class MainActivity : AppCompatActivity() {

    private lateinit var preferenceManager : PreferenceManager
    private lateinit var binding : ActivityMainBinding
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)
        preferenceManager.userName = "Prady"
        Log.e("pref_test", preferenceManager.userName.toString())

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }

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
        }else{
            Log.e("Network", "Not Available")
        }
    }
}