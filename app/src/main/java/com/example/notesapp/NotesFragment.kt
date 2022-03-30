package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentNotesBinding
import java.util.*

class NotesFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNotesBinding
    private var noteText: String = ""
    private lateinit var note: Note
    private var addNote: Boolean = false
    private val repository: NotesRepository by lazy {
        NotesRepository(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            addNote = it.getBoolean("add_note")
            if(!addNote) {
                note = it.getParcelable<Note>("myArg")!!
                noteText = note.noteText
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when {
            addNote -> {
                binding.editBtn.visibility = View.GONE
                binding.deleteBtn.visibility = View.GONE
            }
            else -> {
                binding.editBtn.visibility = View.VISIBLE
                binding.deleteBtn.visibility = View.VISIBLE
            }
        }

        binding.viewEditNoteEditText.setText(noteText)

        binding.editBtn.setOnClickListener(this)
        binding.deleteBtn.setOnClickListener(this)
        binding.saveBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        return when(v){
            binding.editBtn -> {
                binding.viewEditNoteEditText.setText(noteText)
                binding.viewEditNoteEditText.requestFocus()
                binding.viewEditNoteEditText.isCursorVisible = true
            }
            binding.saveBtn -> {
                noteText = binding.viewEditNoteEditText.text.toString()
                when {
                    addNote -> {
                        repository.insertNotes(Note(Date(),noteText))
                        v.snack("New note saved")
                    }
                    else -> {
                        repository.updateNotes(Note(note.dateAdded,noteText))
                        v.snack("note updated")
                    }
                }
            }
            else ->{
                binding.viewEditNoteEditText.text!!.clear()
                binding.viewEditNoteEditText.clearFocus()
                repository.deleteNotes(note)
                v.snack("note deleted")

            }
        }
    }
}