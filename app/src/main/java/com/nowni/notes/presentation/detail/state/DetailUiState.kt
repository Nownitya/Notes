package com.nowni.notes.presentation.detail.state

data class DetailUiState(
    val noteId: Long? = null,
    val title: String = "",
    val content: String = "",
)