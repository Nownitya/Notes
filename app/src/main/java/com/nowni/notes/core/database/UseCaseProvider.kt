package com.nowni.notes.core.database

import android.content.Context
import com.nowni.notes.domain.usecase.note.AddNoteUseCase
import com.nowni.notes.domain.usecase.note.DeleteNoteUseCase
import com.nowni.notes.domain.usecase.note.GetNoteByIdUseCase
import com.nowni.notes.domain.usecase.note.GetNotesUseCase
import com.nowni.notes.domain.usecase.note.NotesUseCases
import com.nowni.notes.domain.usecase.note.UpdateNoteUseCase

object UseCaseProvider {
    fun provideNotesUseCase(
        context: Context
    ): NotesUseCases {
        val repository = RepositoryProvider.provideNoteRepository(context)

        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            getNotesById = GetNoteByIdUseCase(repository),
            addNote = AddNoteUseCase(repository),
            updateNote = UpdateNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
        )
    }
}