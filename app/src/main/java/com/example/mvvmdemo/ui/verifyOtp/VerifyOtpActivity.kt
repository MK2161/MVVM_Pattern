package com.example.mvvmdemo.ui.verifyOtp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.R
import com.example.mvvmdemo.constants.KEY_VERIFICATION_ID
import com.example.mvvmdemo.constants.KEY_VERIFICATION_OTP
import com.example.mvvmdemo.ui.changePassword.ChangePasswordActivity
import kotlinx.android.synthetic.main.activity_verify_otp.*
import org.koin.android.ext.android.inject

class VerifyOtpActivity : AppCompatActivity() {

    private val verifyOtpViewModel: VerifyOtpViewModel by inject()

    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        id = intent.getStringExtra(KEY_VERIFICATION_ID)
        setUpUi()
        setUpListener()
    }

    private fun setUpUi() {
        verifyOtpViewModel.error.observe(this) {
            showErrorMessage(it)
        }
        verifyOtpViewModel.navigation.observe(this) {
            onChangePassword()
        }

    }

    private fun setUpListener() {
        uiBtnVerify.setOnClickListener {
            verifyOtpViewModel.onVerify(
                id = id.toString(),
                otp = uiEtVerificationCode.text.toString()
            )
        }
        uiIvCancel.setOnClickListener{
            finish()
        }
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun onChangePassword() {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        intent.putExtra(KEY_VERIFICATION_OTP, id)
        startActivity(intent)
        finish()
    }

}