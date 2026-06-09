package com.nowni.notes.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@Serializable
data class Editor(val noteId:Long? = null) : NavKey

@Serializable
data class Detail(
    val noteId: Long
) : NavKey

