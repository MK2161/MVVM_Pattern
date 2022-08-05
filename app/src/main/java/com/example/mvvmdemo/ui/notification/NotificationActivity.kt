package com.example.mvvmdemo.ui.notification

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.R
import com.example.mvvmdemo.adapter.ListShowAdapter
import com.example.mvvmdemo.model.NotificationItem
import kotlinx.android.synthetic.main.activity_notification.*
import org.koin.android.ext.android.inject

class NotificationActivity : AppCompatActivity() {

    private val notificationViewModel:NotificationViewModel by inject ()

    private val listShowAdapter by lazy {
        ListShowAdapter(
            notificationItem = arrayListOf(),
            onActionClicked = { onActionClicked(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setUpUi()
    }

    private fun setUpUi() {
        uiRvList.apply {
            adapter = listShowAdapter
        }
        notificationViewModel.notification.observe(this){ data->
            if (data.isNullOrEmpty()) {
                 uiTvNoData.visibility = View.VISIBLE
            } else {
                listShowAdapter.setNotificationItemList(notificationItemList = data)
            }
        }
        notificationViewModel.error.observe(this){
            showErrorMessage(it)
        }
    }

    private fun onActionClicked(notificationItem: NotificationItem) {
        Toast.makeText(this, "message$notificationItem", Toast.LENGTH_SHORT).show()
    }
    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}