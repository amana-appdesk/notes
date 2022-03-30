package com.example.notesapp

import android.content.Context
import android.os.AsyncTask
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import com.example.notesapp.data.NoteDatabase

class NotesRepository(context: Context) {

    var db: NoteDao = NoteDatabase.getDatabase(context).noteDao()

    // get all notes
    fun getAllNotes(): List<Note> {
        return db.getAllNotes()
    }

    // insert new notes
    fun insertNotes(note: Note) {
        insertAsyncTask(db).execute(note)
    }

    // delete notes
    fun deleteNotes(note: Note) {
        db.deleteNote(note)
    }

    // update notes
    fun updateNotes(note: Note) {
        db.updateNote(note)
    }

    private class insertAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            noteDao.insertNote(params[0]!!)
            return null
        }
    }

}