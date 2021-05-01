package com.wiseman.paul.permissionsinandroid

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.wiseman.paul.permissionsinandroid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRequestPermission.setOnClickListener{requestPermission()}
    }

    private fun hasWritePermission() =
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() =
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun hasLocationBackgroundPermission() =
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)== PackageManager.PERMISSION_GRANTED


    private fun requestPermission(){
        var permissionSToRequest = mutableListOf<String>()
        if(!hasWritePermission()){
            permissionSToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasLocationForegroundPermission()){
            permissionSToRequest.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(!hasLocationBackgroundPermission()){
            permissionSToRequest.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if(permissionSToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionSToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0){
            for(i in grantResults.indices){
                if(grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionRequest", "${permissions} is granted")
                }
            }
        }
    }
}