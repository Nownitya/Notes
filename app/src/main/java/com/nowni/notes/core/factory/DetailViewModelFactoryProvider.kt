package com.nowni.notes.core.factory

import android.content.Context
import com.nowni.notes.core.database.UseCaseProvider
import com.nowni.notes.presentation.detail.DetailViewModelFactory

object DetailViewModelFactoryProvider {
    fun provide(context: Context): DetailViewModelFactory {
        val useCases = UseCaseProvider.provideNotesUseCase(context)

        return DetailViewModelFactory(
            getNoteByIdUseCase = useCases.getNotesById,
            deleteNoteUseCase = useCases.deleteNote,
        )
    }
}