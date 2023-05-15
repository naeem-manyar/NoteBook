package com.naeem.notebook.adapter

import com.naeem.notebook.model.NoteBook

interface ClickHandler {

    fun handleLongClick(noteBook: NoteBook)

    fun handleClick(noteBook: NoteBook)

}