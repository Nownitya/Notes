package com.nowni.notes.presentation.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nowni.notes.domain.model.Note
import com.nowni.notes.domain.usecase.note.AddNoteUseCase
import com.nowni.notes.domain.usecase.note.GetNoteByIdUseCase
import com.nowni.notes.domain.usecase.note.UpdateNoteUseCase
import com.nowni.notes.presentation.editor.state.EditorUiAction
import com.nowni.notes.presentation.editor.state.EditorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditorViewModel(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditorUiState())

    val uiState: StateFlow<EditorUiState> = _uiState.asStateFlow()

    fun onAction(
        action: EditorUiAction
    ) {
        when (action) {
            is EditorUiAction.TitleChange -> {
                _uiState.update {
                    it.copy(title = action.title)
                }
            }

            is EditorUiAction.ContentChange -> {
                _uiState.update {
                    it.copy(content = action.content)
                }
            }

            else -> Unit
        }

    }

    fun saveNote(
        noteId: Long? = null
    ) {
        viewModelScope.launch {
            val currentState = _uiState.value

            if (currentState.title.isBlank()) {
                return@launch
            }
            try {
                _uiState.update { it.copy(isSaving = true, errorMessage = null) }

                val note = Note(
                    id = noteId ?: 0L, title = currentState.title, content = currentState.content
                )
                if (noteId == null) {
                    addNoteUseCase(note)
                } else {
                    updateNoteUseCase(note)
                }
            }
            catch (e: IllegalArgumentException){
                _uiState.update { it.copy(errorMessage = e.message) }
            }
            catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Could not save note. Please try again.") }
            } finally {
                _uiState.update { it.copy(isSaving = false) }
            }
        }
    }

    fun loadNote(noteId: Long) {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId) ?: return@launch

            _uiState.update { it.copy(title = note.title, content = note.content) }
        }

    }
}