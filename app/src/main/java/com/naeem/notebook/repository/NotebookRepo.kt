package com.naeem.notebook.repository

import android.content.Context
import com.naeem.notebook.model.NoteBook
import com.naeem.notebook.room.NoteBookDatabase

class NotebookRepo {

    suspend fun insert(context:Context,noteBook: NoteBook){
        NoteBookDatabase.get(context).getNotebookDao().insert(noteBook)
    }

    suspend fun delete(context:Context,noteBook: NoteBook){
        NoteBookDatabase.get(context).getNotebookDao().delete(noteBook)
    }

    suspend fun getAllNotebooks(context:Context):List<NoteBook>{
       return NoteBookDatabase.get(context).getNotebookDao().getAllNotebook()
    }
}