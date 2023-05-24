package com.naeem.notebook.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naeem.notebook.R
import com.naeem.notebook.adapter.ClickHandler
import com.naeem.notebook.adapter.MyAdapter
import com.naeem.notebook.model.NoteBook
import com.naeem.notebook.viewmodel.NoteBookViewModel

class MainFragment : Fragment(), ClickHandler {

    private lateinit var navController: NavController
    private lateinit var viewModel: NoteBookViewModel

    private var adapter = MyAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[NoteBookViewModel::class.java]

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        val fBtnMainNoteBook = view.findViewById<FloatingActionButton>(R.id.fBtnMainNotebook)
        fBtnMainNoteBook.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addFragment)
        }

        val rvMain = view.findViewById<RecyclerView>(R.id.rvMain)

        context?.let { viewModel.getAllNotebooks(it) }
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setContentList(it)
            rvMain.also { rvMain ->
                rvMain.adapter = adapter
            }
        }
    }
    override fun handleLongClick(noteBook: NoteBook) {
        AlertDialog.Builder(requireContext()).setMessage("Are You Sure?").setPositiveButton("Ok"){ _, _ ->
            context?.let { viewModel.delete(it,noteBook) }
        }
            .setNegativeButton("Cancel"){_,_ ->}.show()
    }

    override fun handleClick(noteBook: NoteBook) {
        val bundle= bundleOf("note_book" to noteBook)
        navController.navigate(R.id.action_mainFragment_to_editFragment,bundle)
    }
}