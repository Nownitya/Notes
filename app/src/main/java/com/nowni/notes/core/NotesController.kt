package com.nowni.notes.core

import com.nowni.notes.domain.model.Note
import com.nowni.notes.domain.usecase.note.NotesUseCases
import kotlinx.coroutines.flow.Flow

class NotesController(
    private val useCases: NotesUseCases
) {
    fun getNotes(): Flow<List<Note>> {
        return useCases.getNotes()
    }

    suspend fun addNote(note: Note) {
        useCases.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        useCases.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        useCases.deleteNote(
            note
        )
    }
}