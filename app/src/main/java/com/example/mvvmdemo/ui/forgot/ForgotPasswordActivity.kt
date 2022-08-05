package com.example.mvvmdemo.ui.forgot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.R
import com.example.mvvmdemo.constants.KEY_EMAIL_ID
import com.example.mvvmdemo.ui.chooseOption.ChooseOptionActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.koin.android.ext.android.inject

class ForgotPasswordActivity:AppCompatActivity() {

    private val forgotPasswordViewModel : ForgotPasswordViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        setUpUi()
        setUpListener()
    }

    private fun setUpUi() {
        forgotPasswordViewModel.error.observe(this){ message->
            showErrorMessage(message)
        }
        forgotPasswordViewModel.navigationEmail.observe(this){
            onNavigationEmailId()
        }
    }

    private fun setUpListener() {
        uiBtnEnter.setOnClickListener {
            forgotPasswordViewModel.onVerifyEmail(
                email = uiEtEmail.text.toString()
            )
        }
        uiIvFinish.setOnClickListener{
            finish()
        }
    }

    private fun showErrorMessage(error: String){
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
    }

    private fun onNavigationEmailId(){
        val intent = Intent(this, ChooseOptionActivity::class.java)
        intent.putExtra(KEY_EMAIL_ID,uiEtEmail.text.toString())
        startActivity(intent)
        finish()
    }
}