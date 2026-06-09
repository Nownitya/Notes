package com.nowni.notes.data.mapper

import com.nowni.notes.data.local.entity.NoteEntity
import com.nowni.notes.domain.model.Note

//  Converts a Database Entity INTO a clean UI Model.
fun NoteEntity.toNote(): Note{
    return Note(
        id = id,
        title = title,
        content = content,
    )
}

//  Convert a UI Model BACK INTO a Database Entity.
fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
    )
}