package com.swapnil.locationbackground

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.swapnil.locationbackground.service.LocationService

class MainActivity : AppCompatActivity() {
    private val TAG = "SWAPNILIFTTT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check & Request Permission to Location
        if (!checkPermission()) {
            Log.d(TAG, "Requesting Permission")
            requestPermission()
        } else {
            Log.d(TAG, "Has Permission")
            //startLocationService()
        }

        findViewById<Button>(R.id.click).setOnClickListener { startLocationService() }


    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 2703)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 2703 ) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Got the Permission")
                //startLocationService()
            }
        }
    }

    private fun startLocationService() {
        Log.d(TAG, "Starting Service")
        ContextCompat.startForegroundService(this, Intent(this, LocationService::class.java))
    }
}