package com.example.mvvmdemo.ui.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mvvmdemo.R
import com.example.mvvmdemo.ui.document.DocumentActivity
import com.example.mvvmdemo.ui.forgot.ForgotPasswordActivity
import com.example.mvvmdemo.ui.menuOption.MenuOptionActivity
import com.example.mvvmdemo.ui.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.security.Permission

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by inject()
    private val CAMERA_PERMISSION_CODE = 10
    private val STORAGE_PERMISSION_CODE = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        setUpListener()
    }


    private fun setUpUi() {
        loginViewModel.error.observe(this) {
            showErrorMessage(it)
        }
        loginViewModel.navigation.observe(this) {
            navigationToMenuOptionScreen()
        }
    }

    private fun setUpListener() {
        uiBtnLogin.setOnClickListener {
            loginViewModel.onVerifyLogin(
                email = uiEtEmail.text.toString(),
                password = uiEtPassword.text.toString()
            )

        }
        uiBtnResetPassword.setOnClickListener {
            navigationToForgotScreen()
        }
    }

    private fun navigationToMenuOptionScreen() {
        val intent = Intent(this, MenuOptionActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun navigationToForgotScreen() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)

    }



}