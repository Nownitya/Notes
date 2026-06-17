package com.nowni.notes.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nowni.notes.domain.usecase.note.DeleteNoteUseCase
import com.nowni.notes.domain.usecase.note.GetNoteByIdUseCase
import com.nowni.notes.presentation.detail.state.DetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadNote(noteId: Long) {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId) ?: return@launch

            _uiState.value = DetailUiState(
                noteId = note.id,
                title = note.title,
                content = note.content,
            )
        }
    }

    fun deleteNote() {
        viewModelScope.launch {

            val state = uiState.value

            val noteId = state.noteId ?: return@launch

            val note = getNoteByIdUseCase(noteId) ?: return@launch

            deleteNoteUseCase(note)
        }
    }

}

