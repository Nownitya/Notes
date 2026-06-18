package com.nowni.notes.domain.usecase.note

import com.nowni.notes.domain.model.Note
import com.nowni.notes.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke (
        note: Note
    ) {
        require(note.title.isNotBlank()){
            "Title cannot be empty"
        }
        repository.insertNote(note)
    }
}