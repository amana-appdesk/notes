package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.OnItemClick
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.NotesPreviewListBinding

class PreviewNotesAdapter(private val previewNotesList: MutableList<Note>, private val notesClick: OnItemClick) :
    RecyclerView.Adapter<PreviewNotesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            NotesPreviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note: Note = previewNotesList[position]
        holder.bind(note.noteText,notesClick,position)
    }

    override fun getItemCount(): Int = previewNotesList.size

    class MyViewHolder(private val binding: NotesPreviewListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String, itemClick: OnItemClick, position: Int) {
            binding.previewTextView.text = text

            binding.previewTextView.setOnClickListener {
                itemClick.notesClick(position)
            }
        }
    }
}