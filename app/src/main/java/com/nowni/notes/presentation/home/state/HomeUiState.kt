package com.nowni.notes.presentation.home.state

import com.nowni.notes.domain.model.Note

data class HomeUiState(val notes: List<Note> = emptyList())