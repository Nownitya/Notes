package com.nowni.notes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nowni.notes.domain.usecase.note.GetNotesUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(getNotesUseCase = getNotesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}