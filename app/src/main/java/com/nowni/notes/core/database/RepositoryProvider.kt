package com.nowni.notes.core.database

import android.content.Context
import com.nowni.notes.data.repository.NoteRepositoryImpl
import com.nowni.notes.domain.repository.NoteRepository

object RepositoryProvider {

    fun provideNoteRepository(
        context: Context
    ): NoteRepository {

        return NoteRepositoryImpl(
            DatabaseProvider
                .getDatabase(context)
                .noteDao()
        )
    }
}