package com.nowni.notes.presentation.editor.state

sealed interface EditorUiAction {
    data class TitleChange(
        val title: String
    ): EditorUiAction

    data class ContentChange(
        val content: String
    ): EditorUiAction

    data object SaveNote : EditorUiAction

    data object NavigateBack : EditorUiAction
}