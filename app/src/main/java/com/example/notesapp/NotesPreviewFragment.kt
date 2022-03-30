package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.adapter.PreviewNotesAdapter
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDatabase
import com.example.notesapp.databinding.FragmentNotesPreviewBinding

class NotesPreviewFragment : Fragment(), OnItemClick {

    private val previewNotesList = mutableListOf<Note>()
    private lateinit var binding: FragmentNotesPreviewBinding
    private lateinit var previewNotesAdapter: PreviewNotesAdapter

    private val notesRepository by lazy {
        NotesRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesPreviewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previewNotesAdapter = PreviewNotesAdapter(previewNotesList,this)
        binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.notesRecyclerView.setHasFixedSize(true)
        binding.notesRecyclerView.adapter = previewNotesAdapter

        fetchAllNotes()

        binding.addNoteBtn.setOnClickListener {
            val action = NotesPreviewFragmentDirections.actionNotesPreviewFragmentToNotesFragment(true,null)
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    override fun notesClick(position: Int) {
        val str = previewNotesList[position]
        val action = NotesPreviewFragmentDirections.actionNotesPreviewFragmentToNotesFragment(false,str)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun fetchAllNotes(){
        previewNotesList.clear()
        if(notesRepository.getAllNotes().isNotEmpty()){
            previewNotesList.addAll(notesRepository.getAllNotes())
            previewNotesAdapter.notifyDataSetChanged()
        }

    }
}