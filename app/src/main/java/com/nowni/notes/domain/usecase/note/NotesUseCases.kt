package com.nowni.notes.domain.usecase.note

import javax.inject.Inject

data class NotesUseCases @Inject constructor(
    val getNotes: GetNotesUseCase,
    val getNotesById: GetNoteByIdUseCase,
    val addNote: AddNoteUseCase,
    val updateNote: UpdateNoteUseCase,
    val deleteNote: DeleteNoteUseCase
)
