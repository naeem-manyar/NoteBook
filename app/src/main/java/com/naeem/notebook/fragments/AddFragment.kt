package com.naeem.notebook.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naeem.notebook.R
import com.naeem.notebook.model.NoteBook
import com.naeem.notebook.viewmodel.NoteBookViewModel

class AddFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel:NoteBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[NoteBookViewModel::class.java]

        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        val etAddTitle = view.findViewById<EditText>(R.id.etAddTitle)
        val etAddDesc = view.findViewById<EditText>(R.id.etAddDesc)

        val fBtnAddNoteBook = view.findViewById<FloatingActionButton>(R.id.fBtnAddNotebook)
        fBtnAddNoteBook.setOnClickListener {

            if (etAddTitle.text.trim().toString().isEmpty()) return@setOnClickListener

            val noteBook=NoteBook(etAddTitle.text.trim().toString(),etAddDesc.text.trim().toString())
            saveInDb(noteBook)

            navController.navigate(R.id.action_addFragment_to_mainFragment)
        }

    }
    private fun saveInDb(noteBook: NoteBook){
        context?.let { viewModel.insert(it,noteBook) }
    }

}