package com.nowni.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nowni.notes.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("Select * from notes WHERE id = :noteId")
    suspend fun getNoteById(
        noteId: Long
    ): NoteEntity?

    @Insert
    suspend fun insertNote(
        note: NoteEntity
    )

    @Update
    suspend fun updateNote(
        note: NoteEntity
    )

    @Delete
    suspend fun deleteNote(
        note: NoteEntity
    )
}