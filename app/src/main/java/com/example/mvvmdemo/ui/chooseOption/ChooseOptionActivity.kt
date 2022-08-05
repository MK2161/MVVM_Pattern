package com.example.mvvmdemo.ui.chooseOption

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mvvmdemo.R
import com.example.mvvmdemo.constants.EMAIL_VERIFICATION
import com.example.mvvmdemo.constants.KEY_EMAIL_ID
import com.example.mvvmdemo.constants.KEY_VERIFICATION_ID
import com.example.mvvmdemo.constants.MOBILE_VERIFICATION
import com.example.mvvmdemo.ui.verifyOtp.VerifyOtpActivity
import kotlinx.android.synthetic.main.activity_choose_option.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class ChooseOptionActivity : AppCompatActivity() {


    private val chooseOptionViewModel: ChooseOptionViewModel by inject()

    private var email: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_option)
        email = intent.getStringExtra(KEY_EMAIL_ID)
        setUpUi()
        setUpListener()
    }

    private fun setUpUi() {

        chooseOptionViewModel.navigation.observe(this) { id ->
            onNavigationOtp(id)
        }
        chooseOptionViewModel.error.observe(this) { message ->
            showErrorMessage(message)
        }
    }

    private fun setUpListener() {
        uiBtnCallMobile.setOnClickListener {
            chooseOptionViewModel.onRequestOtp(
                email = email.toString(),
                verificationType = MOBILE_VERIFICATION
            )
        }
        uiBtnCallEmail.setOnClickListener {
            chooseOptionViewModel.onRequestOtp(
                email = email.toString(),
                verificationType = EMAIL_VERIFICATION
            )
        }
        uiIvEnd.setOnClickListener {
            finish()
        }
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun onNavigationOtp(verificationId: String) {
        val intent = Intent(this, VerifyOtpActivity::class.java)
        intent.putExtra(KEY_VERIFICATION_ID, verificationId)
        startActivity(intent)
        finish()
    }


}