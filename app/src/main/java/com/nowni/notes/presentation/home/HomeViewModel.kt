package com.nowni.notes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nowni.notes.domain.usecase.note.GetNotesUseCase
import com.nowni.notes.presentation.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
//    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getNotesUseCase()
                .catch { error ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = error.message ?: "An unexpected error occurred"
                        )
                    }
                }
                .collect { notes ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            notes = notes,
                            isLoading = false
                        )
                    }
                }
        }
    }

//    fun onDeleteNote(note: Note) {
//        viewModelScope.launch {
//            try {
//                deleteNoteUseCase(note)
//            } catch (e: Exception) {
//                _uiState.update { it.copy(
//                    error = e.message ?: "Failed to delete note"
//                ) }
//            }
//        }
//    }
}