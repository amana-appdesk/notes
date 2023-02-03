package com.example.notesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import com.example.notesapp.data.NoteDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest : TestCase(){

    private lateinit var db: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,NoteDatabase::class.java).build()
        dao = db.noteDao()
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun writeAndReadNote() = runBlocking {
        val note = Note(Date(),"sample")
        dao.insertNote(note)
        val getNotes = dao.getAllNotes()
        assertThat(getNotes.contains(note)).isTrue()
    }
}