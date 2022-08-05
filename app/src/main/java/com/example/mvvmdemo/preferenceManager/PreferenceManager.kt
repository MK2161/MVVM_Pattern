package com.example.mvvmdemo.preferenceManager


import android.content.Context
import androidx.core.content.edit


class PreferenceManager(context: Context) {
    companion object {
        private const val KEY_USER_NAME = "key.user.user.name"
        private const val KEY_STAFF_ACCESS_TOKEN = "key.user.staff.access_token"
        private const val KEY_STAFF_EMAIL = "key.user.staff.email"
        private const val KEY_STAFF_REFRESH_TOKEN = "key.user.staff.refresh_token"
        private const val KEY_STAFF_ID = "key.user.staff.id"

    }

    private val appPreference = context.getSharedPreferences("AppData", Context.MODE_PRIVATE)

    fun setStaffId(emailId: String?) {
        appPreference.edit {
            putString(KEY_STAFF_ID, emailId)
        }
    }

    // fun getUserName() = appPreference.getString(KEY_USER_NAME, null )
    fun getStaffId() = appPreference.getString(KEY_STAFF_ID, "")

    fun setAccessToken(accessToken: String?) {
        appPreference.edit {
            putString(KEY_STAFF_ACCESS_TOKEN, accessToken)
        }
    }

    fun setRefreshToken(referenceToken: String?) {
        appPreference.edit {
            putString(KEY_STAFF_REFRESH_TOKEN, referenceToken)
        }
    }

    fun getAccessToken() = appPreference.getString(KEY_STAFF_ACCESS_TOKEN, "")
    fun getRefreshToken() = appPreference.getString(KEY_STAFF_REFRESH_TOKEN, "")


}