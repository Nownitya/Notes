package com.nowni.notes.core.database

import android.content.Context
import androidx.room.Room
import com.nowni.notes.data.local.database.NotesDatabase

object DatabaseProvider {

    @Volatile
    private var INSTANCE: NotesDatabase? = null

    fun getDatabase(context: Context): NotesDatabase {

        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                "notes_database"
            ).build()
                .also {
                    INSTANCE = it
                }
        }
    }
}