package com.example.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note:Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("Select * from notes ORDER BY dateAdded DESC")
    fun getAllNotes(): List<Note>
}