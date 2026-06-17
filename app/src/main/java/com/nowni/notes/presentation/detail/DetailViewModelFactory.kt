package com.nowni.notes.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nowni.notes.domain.usecase.note.DeleteNoteUseCase
import com.nowni.notes.domain.usecase.note.GetNoteByIdUseCase

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(
                getNoteByIdUseCase = getNoteByIdUseCase,
                deleteNoteUseCase = deleteNoteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}