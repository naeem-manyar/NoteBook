package com.naeem.notebook.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naeem.notebook.model.NoteBook
import com.naeem.notebook.repository.NotebookRepo
import kotlinx.coroutines.launch

class NoteBookViewModel:ViewModel() {
    private val repo=NotebookRepo()
    val list=MutableLiveData<List<NoteBook>>()

    fun insert(context: Context,noteBook:NoteBook){
        viewModelScope.launch {
            repo.insert(context, noteBook)
        }
    }

    fun delete(context: Context,noteBook:NoteBook){
        viewModelScope.launch {
            repo.delete(context, noteBook)
            list.value=repo.getAllNotebooks(context)
        }
    }

    fun getAllNotebooks(context: Context){
        viewModelScope.launch {
            list.value=repo.getAllNotebooks(context)
        }
    }
}