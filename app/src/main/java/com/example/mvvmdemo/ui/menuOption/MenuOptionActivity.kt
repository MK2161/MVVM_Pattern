package com.example.mvvmdemo.ui.menuOption

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mvvmdemo.R
import com.example.mvvmdemo.ui.document.DocumentActivity
import com.example.mvvmdemo.ui.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_menu_option.*

class MenuOptionActivity : AppCompatActivity() {


    private val requestPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            validatePermission(it)
        }
    }
    private val multiplePermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            validateMultiplePermission(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_option)
        requestPermissionLauncher
        multiplePermissionLauncher
        setUpListener()

    }

    private fun setUpListener() {
        uiBtnNotification.setOnClickListener {
            checkPermission()

        }
        uiBtnDocument.setOnClickListener {
            checkMultiplePermission()
        }
        uiIvEnd.setOnClickListener {
            finish()
        }
    }

    private fun navigationToNotificationScreen() {
        val intent = Intent(this, NotificationActivity::class.java)
        startActivity(intent)
    }

    private fun navigationToDocumentScreen() {
        val intent = Intent(this, DocumentActivity::class.java)
        startActivity(intent)
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            validatePermission(isPermission = true)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun validatePermission(isPermission: Boolean) {
        if (isPermission) {
            navigationToNotificationScreen()
        } else {
            Toast.makeText(this@MenuOptionActivity, " Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkMultiplePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            validateMultiplePermission(mapOf("" to true) as MutableMap<String, Boolean>,)
        } else {
            multiplePermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                )
            )
        }
    }

    private fun validateMultiplePermission(permission: MutableMap<String, Boolean>) {
        permission.onEach { (_, permission) ->
            if (!permission) {
                return  Toast.makeText(this,"Success Denied",Toast.LENGTH_SHORT).show()
            }

        }
        navigationToDocumentScreen()
    }

}