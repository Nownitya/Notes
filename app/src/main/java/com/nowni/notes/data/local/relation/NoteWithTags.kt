package com.nowni.notes.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nowni.notes.data.local.entity.NoteEntity
import com.nowni.notes.data.local.entity.NoteTagCrossRef
import com.nowni.notes.data.local.entity.TagEntity

data class NoteWithTags(
    @Embedded
    val note: NoteEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "Id",
        associateBy = Junction(
            NoteTagCrossRef::class
        )
    )
    val tags: List<TagEntity>
)