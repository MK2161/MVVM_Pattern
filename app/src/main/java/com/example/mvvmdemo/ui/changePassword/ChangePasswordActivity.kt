package com.example.mvvmdemo.ui.changePassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.R
import com.example.mvvmdemo.constants.KEY_VERIFICATION_OTP
import com.example.mvvmdemo.ui.success.SuccessActivity
import kotlinx.android.synthetic.main.activity_change_password.*
import org.koin.android.ext.android.inject

class ChangePasswordActivity:AppCompatActivity() {

    private val changePasswordViewModel:ChangePasswordViewModel by inject()

    private var verificationId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        verificationId = intent.getStringExtra(KEY_VERIFICATION_OTP)
        setUpUi()
        setUpListener()
    }

    private fun setUpUi() {
        changePasswordViewModel.error.observe(this){
            showErrorMessage(it)
        }
        changePasswordViewModel.navigation.observe(this){
            onSuccessPassword()
        }
    }

    private fun setUpListener() {
        uiBtnVerify.setOnClickListener{
            changePasswordViewModel.onVerify(
                password = uiEtChangePassword.text.toString(),
                verificationId = verificationId.toString(),
                confirmPassword = uiEtConfirmPassword.text.toString()
            )
        }
        uiIvClose.setOnClickListener {
            finish()
        }

    }

    private fun showErrorMessage(error: String){
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccessPassword() {
        val intent = Intent(this, SuccessActivity::class.java)
        startActivity(intent)
        finish()
    }
}