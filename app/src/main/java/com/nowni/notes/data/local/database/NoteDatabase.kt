package com.nowni.notes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nowni.notes.data.local.dao.NoteDao
import com.nowni.notes.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}