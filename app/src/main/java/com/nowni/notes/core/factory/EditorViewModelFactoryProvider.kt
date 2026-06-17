package com.nowni.notes.core.factory

import android.content.Context
import com.nowni.notes.core.database.UseCaseProvider
import com.nowni.notes.presentation.editor.EditorViewModelFactory

object EditorViewModelFactoryProvider {
    fun provide(context: Context): EditorViewModelFactory{
        val useCases = UseCaseProvider.provideNotesUseCase(context)

        return EditorViewModelFactory(
            addNoteUseCase = useCases.addNote,
            updateNoteUseCase = useCases.updateNote,
            getNoteByIdUseCase = useCases.getNotesById
        )
    }

}