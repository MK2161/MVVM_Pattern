package com.example.mvvmdemo.ui.success

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity:AppCompatActivity() {

    private var correctPassword:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        setUpListener()

    }
    private fun setUpListener() {
        uiBtnSignIn.setOnClickListener{
            finish()
        }
        uiIvBack.setOnClickListener{
            finish()
        }
    }
}