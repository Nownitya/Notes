package com.nowni.notes.domain.usecase.note

data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val getNotesById: GetNoteByIdUseCase,
    val addNote: AddNoteUseCase,
    val updateNote: UpdateNoteUseCase,
    val deleteNote: DeleteNoteUseCase
)
