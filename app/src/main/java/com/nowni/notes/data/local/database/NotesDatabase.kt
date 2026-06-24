package com.nowni.notes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nowni.notes.data.local.dao.NoteDao
import com.nowni.notes.data.local.dao.TagDao
import com.nowni.notes.data.local.entity.NoteEntity
import com.nowni.notes.data.local.entity.NoteTagCrossRef
import com.nowni.notes.data.local.entity.TagEntity

@Database(
    entities = [
        NoteEntity::class,
        TagEntity::class,
        NoteTagCrossRef::class
    ],
    version = 2,
    exportSchema = true
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun tagDao(): TagDao

}