package com.nowni.notes.domain.usecase.note

import com.nowni.notes.domain.model.Note
import com.nowni.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
){
    operator fun invoke(): Flow<List<Note>>{
        return repository.getNotes()
    }

}