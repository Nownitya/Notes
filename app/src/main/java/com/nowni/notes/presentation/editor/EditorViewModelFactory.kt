package com.nowni.notes.presentation.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nowni.notes.domain.usecase.note.AddNoteUseCase
import com.nowni.notes.domain.usecase.note.GetNoteByIdUseCase
import com.nowni.notes.domain.usecase.note.UpdateNoteUseCase

@Suppress("UNCHECKED_CAST")
class EditorViewModelFactory(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditorViewModel::class.java)){
            return EditorViewModel(
                addNoteUseCase = addNoteUseCase,
                updateNoteUseCase = updateNoteUseCase,
                getNoteByIdUseCase = getNoteByIdUseCase
            ) as T
        }
        throw IllegalArgumentException(
            "Unknown viewModel class"
        )
    }
}