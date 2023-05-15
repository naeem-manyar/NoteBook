package com.naeem.notebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naeem.notebook.R
import com.naeem.notebook.model.NoteBook

class MyAdapter(val clickHandler: ClickHandler): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var listNoteBook = listOf<NoteBook>()

    fun setContentList(listNoteBook:List<NoteBook>){
        this.listNoteBook = listNoteBook
        notifyDataSetChanged()
    }


    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)
        val rlContainer: RelativeLayout = view.findViewById(R.id.rlContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_notebook,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return this.listNoteBook.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = listNoteBook[position].title
        holder.tvDesc.text = listNoteBook[position].desc
        holder.rlContainer.setOnLongClickListener {
            clickHandler.handleLongClick(listNoteBook[position])
            return@setOnLongClickListener true
        }
        holder.rlContainer.setOnClickListener {
            clickHandler.handleClick(listNoteBook[position])
        }
    }
}