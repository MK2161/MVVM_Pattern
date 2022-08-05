package com.example.mvvmdemo.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.model.NotificationItem
import kotlinx.android.synthetic.main.activity_recyclerview_notification.view.*

class ListShowAdapter(
    private val notificationItem: ArrayList<NotificationItem>,
    private val onActionClicked: (NotificationItem) -> Unit,
) :
    RecyclerView.Adapter<ListShowAdapter.ListShowViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_recyclerview_notification, parent, false)
        return ListShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListShowViewHolder, position: Int) {
        val inputNotificationResponse = notificationItem[position]
        with(holder) {
            uiTvNotificationTime.text = inputNotificationResponse.timeAgo
            uiTvNotificationTitle.text = inputNotificationResponse.title
            uiTvNotificationDescription.text = inputNotificationResponse.description
            uiTvNotificationStatus.text = inputNotificationResponse.notificationType
        }
    }

    override fun getItemCount(): Int {
        return notificationItem.size
    }

    inner class ListShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val uiTvNotificationTime: AppCompatTextView = view.uiTvNotificationTime
        val uiTvNotificationTitle: AppCompatTextView = view.uiTvNotificationTitle
        val uiTvNotificationDescription: AppCompatTextView = view.uiTvNotificationDescription
        val uiTvNotificationStatus: AppCompatTextView = view.uiTvNotificationStatus
        val uiTvNotificationAction: AppCompatTextView = view.uiTvNotificationAction

        init {
            uiTvNotificationAction.setOnClickListener {
                onActionClicked.invoke(notificationItem[adapterPosition])
            }
        }

    }
    fun setNotificationItemList(notificationItemList: ArrayList<NotificationItem>){
        notificationItem.clear()
        notificationItem.addAll(notificationItemList)
        notifyDataSetChanged()
    }

}




