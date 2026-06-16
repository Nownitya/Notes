package com.nowni.notes.presentation.home.state

sealed interface HomeUiAction {

    data object AddNoteClicked : HomeUiAction

    data class NoteClicked(
        val noteId: Long
    ) : HomeUiAction

    data class DeleteNote(
        val noteId: Long
    ) : HomeUiAction

    data object RefreshNotes : HomeUiAction
}