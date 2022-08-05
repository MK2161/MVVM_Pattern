package com.example.mvvmdemo.handler

import com.example.mvvmdemo.constants.ERROR_MESSAGE
import com.example.mvvmdemo.constants.SERVER_ERROR

data class ServiceException(
   val code: String? = SERVER_ERROR,
   val message :String? = ERROR_MESSAGE
)




