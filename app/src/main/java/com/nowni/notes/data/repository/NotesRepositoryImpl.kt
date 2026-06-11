package com.nowni.notes.data.repository

import com.nowni.notes.data.local.dao.NoteDao
import com.nowni.notes.data.mapper.toEntity
import com.nowni.notes.data.mapper.toNote
import com.nowni.notes.domain.model.Note
import com.nowni.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class NotesRepositoryImpl(private val noteDao: NoteDao) : NotesRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { entities ->
            entities.map { entity ->
                entity.toNote()
            }
        }
    }

    override suspend fun getNoteById(noteId: Long): Note? {
        return noteDao.getNoteById(noteId)?.toNote()
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }
}