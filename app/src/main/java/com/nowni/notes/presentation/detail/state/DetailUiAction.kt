package com.nowni.notes.presentation.detail.state

sealed interface DetailUiAction {

    data object NavigateBack : DetailUiAction

    data object EditNote : DetailUiAction
}
