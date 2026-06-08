package com.nowni.notes.presentation.detail.state

data class DetailUiState(
    val noteId: Long = 0L,
    val title: String = "",
    val content: String = "",
)