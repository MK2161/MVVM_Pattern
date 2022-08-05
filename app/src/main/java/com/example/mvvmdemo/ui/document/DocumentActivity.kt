package com.example.mvvmdemo.ui.document

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.mvvmdemo.R
import com.example.mvvmdemo.adapter.DocumentListAdapter
import com.example.mvvmdemo.handler.hideKeyBoard
import com.example.mvvmdemo.handler.showKeyBoard
import com.example.mvvmdemo.model.DocumentItem
import kotlinx.android.synthetic.main.activity_document.*
import org.koin.android.ext.android.inject

class DocumentActivity : AppCompatActivity() {

    private val documentViewModel: DocumentViewModel by inject()

    private val documentListAdapter by lazy {
        DocumentListAdapter(
            documentItem = arrayListOf(),
            onActionClicked = { onAction(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
        setUpUi()
        setUpListener()

    }

    private fun setUpUi() {
        setUpAdapter()
        initUi()
        documentViewModel.error.observe(this) {
            showErrorMessage(it)
        }
        documentViewModel.documentation.observe(this) { documentList ->
            if (documentList.isNullOrEmpty()) {
                onDocumentEmpty()
            } else {
                onDocumentNotEmpty()
                documentListAdapter.setDocumentList(documentItemList = documentList)
            }
        }
        documentViewModel.starredDocumentation.observe(this) { documentList ->
            if (documentList.isNullOrEmpty()) {
                onDocumentEmpty()
            } else {
                onDocumentNotEmpty()
                documentListAdapter.setDocumentList(documentItemList = documentList)
            }
        }

    }

    private fun setUpAdapter() {
        uiRvDocumentList.apply {
            adapter = documentListAdapter
        }
    }

    private fun setUpListener() {
        uiBtnStarred.setOnClickListener {
            documentViewModel.starred(true)
            uiBtnStarred.setBackgroundColor(Color.RED)
            uiBtnAllDocument.setBackgroundColor(Color.BLUE)
            documentViewModel.getStarredDocumentList()
        }

        uiBtnAllDocument.setOnClickListener {
            documentViewModel.starred(false)
            documentListAdapter.setDocumentList(documentViewModel.documentList)
            uiBtnAllDocument.setBackgroundColor(Color.RED)
            uiBtnStarred.setBackgroundColor(Color.BLUE)
            Log.d("DocumentViewModel", "calculaci is${documentViewModel.documentList}")
        }
        initSearch()

    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun onAction(documentItem: DocumentItem) {
        Toast.makeText(this, "message$documentItem", Toast.LENGTH_SHORT).show()
    }

    private fun onDocumentEmpty() {
        uiRvDocumentList.visibility = GONE
        uiTvDocuments.visibility = VISIBLE
    }

    private fun onDocumentNotEmpty() {
        uiRvDocumentList.visibility = VISIBLE
        uiTvDocuments.visibility = GONE
    }

    private fun initUi() {
        uiBtnAllDocument.setBackgroundColor(Color.RED)
        uiBtnStarred.setBackgroundColor(Color.BLUE)
    }

    private fun initSearch() {
        uiIvSearch.setOnClickListener {
            uiIvSearch.visibility = GONE
            uiEtSearch.apply {
                setText("")
                visibility = VISIBLE
                setTextColor(Color.BLACK)
                requestFocus()
            }
            uiImgClear.apply {
                visibility = VISIBLE
                setBackgroundColor(Color.BLACK)
            }
            showKeyBoard(uiEtSearch)
        }

        uiEtSearch.doAfterTextChanged { text: Editable? ->
            uiImgClear.visibility = VISIBLE
            documentViewModel.filterList(text.toString())

        }

        uiImgClear.setOnClickListener {
            hideKeyBoard(it)
            uiEtSearch.setText("")
            uiImgClear.visibility = GONE
        }

    }
}