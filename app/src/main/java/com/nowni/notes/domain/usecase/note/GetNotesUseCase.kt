package com.nowni.notes.domain.usecase.note

import com.nowni.notes.domain.model.Note
import com.nowni.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
){
    operator fun invoke(): Flow<List<Note>>{
        return repository.getNotes()
    }

}