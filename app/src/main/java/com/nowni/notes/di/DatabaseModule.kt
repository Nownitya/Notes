package com.nowni.notes.di

import android.content.Context
import androidx.room.Room
import com.nowni.notes.data.local.dao.NoteDao
import com.nowni.notes.data.local.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(
        database: NotesDatabase
    ): NoteDao {
        return database.noteDao()
    }
}