package com.example.mvvmdemo.ui.document

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.Repository.DocumentRepository
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.model.DocumentItem
import kotlinx.coroutines.launch

class DocumentViewModel(
    private val documentRepository: DocumentRepository,
) : ViewModel() {

    val documentList: ArrayList<DocumentItem> = arrayListOf()
    private val starredList: ArrayList<DocumentItem> = arrayListOf()
    var isStarred: Boolean = false
        private set


    private val documentationLd = MutableLiveData<ArrayList<DocumentItem>>()
    private val errorMessageLd = MutableLiveData<String>()
    private val starredDocumentationLD: MutableLiveData<ArrayList<DocumentItem>> = documentationLd


    val error: LiveData<String> = errorMessageLd
    val documentation: LiveData<ArrayList<DocumentItem>> = documentationLd
    val starredDocumentation: LiveData<ArrayList<DocumentItem>> = starredDocumentationLD


    init {
        viewModelScope.launch {
            when (val response = documentRepository.getDocumentList()) {
                is CustomResponse.Success -> {
                    documentList.addAll(response.data.documentList)
                    documentationLd.value = documentList
                    Log.d("DocumentViewModel", "calculateds is${documentationLd.value}")
                }
                is CustomResponse.Failure -> errorMessageLd.value =
                    response.error.message.toString()
            }
        }
    }

    fun getStarredDocumentList() {
        viewModelScope.launch {
            when (val response = documentRepository.getDocumentList(true)) {
                is CustomResponse.Success -> {
                    starredList.addAll(response.data.documentList)
                    starredDocumentationLD.value =
                        starredList
                }
                is CustomResponse.Failure -> errorMessageLd.value =
                    response.error.message.toString()
            }
        }
    }

    fun filterList(query: String?) {
        val filteredList: ArrayList<DocumentItem> = arrayListOf()
        if (query.isNullOrBlank()) {
            documentationLd.value = documentList
        } else {
            if (!isStarred) {
                documentList.forEach { document ->
                    if (document.name?.contains(query ?: "", true) == true) {
                        filteredList.add(document)
                    }
                }
            } else {
                starredList.forEach { document ->
                    if (document.name?.contains(query ?: "", true) == true) {
                        filteredList.add(document)
                    }
                }
            }
            documentationLd.value = filteredList
        }
    }

    fun starred(starred: Boolean) {
        isStarred = starred
    }

}