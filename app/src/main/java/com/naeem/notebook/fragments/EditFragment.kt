package com.naeem.notebook.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.naeem.notebook.R
import com.naeem.notebook.model.NoteBook
import com.naeem.notebook.viewmodel.NoteBookViewModel

class EditFragment : Fragment() {

    lateinit var navController: NavController

    lateinit var viewModel: NoteBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel= ViewModelProvider(this)[NoteBookViewModel::class.java]

        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController= Navigation.findNavController(view)
        val notebook:NoteBook= requireArguments().get("note_book") as NoteBook

        val etEditTitle=view.findViewById<EditText>(R.id.etEditTitle)
        val etEditDesc=view.findViewById<EditText>(R.id.etEditDesc)

        etEditTitle.setText(notebook.title)
        etEditDesc.setText(notebook.desc)

        val imgSaveInstance=view.findViewById<ImageView>(R.id.imgSaveInstance)

        imgSaveInstance.setOnClickListener {
            notebook.title = etEditTitle.text.trim().toString()
            notebook.desc = etEditDesc.text.trim().toString()

            updateNoteBook(notebook)
        }
    }

    private fun updateNoteBook(noteBook: NoteBook){

        if(noteBook.title.trim().isEmpty()) {
            Toast.makeText(requireContext(),"Title must not be empty",Toast.LENGTH_SHORT).show()
            return
        }

        context?.let { viewModel.insert(it,noteBook) }
        navController.navigate(R.id.action_editFragment_to_mainFragment)
    }
}