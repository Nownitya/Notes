package com.nowni.notes.di

import com.nowni.notes.domain.repository.NoteRepository
import com.nowni.notes.domain.usecase.note.AddNoteUseCase
import com.nowni.notes.domain.usecase.note.DeleteNoteUseCase
import com.nowni.notes.domain.usecase.note.GetNoteByIdUseCase
import com.nowni.notes.domain.usecase.note.GetNotesUseCase
import com.nowni.notes.domain.usecase.note.NotesUseCases
import com.nowni.notes.domain.usecase.note.SearchNotesUseCase
import com.nowni.notes.domain.usecase.note.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideNotesUseCase(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            getNotesById = GetNoteByIdUseCase(repository),
            addNote = AddNoteUseCase(repository),
            updateNote = UpdateNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            searchNotes = SearchNotesUseCase(repository)
        )
    }
}