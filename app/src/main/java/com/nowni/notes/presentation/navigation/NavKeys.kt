package com.nowni.notes.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@Serializable
data object Editor : NavKey

@Serializable
data class Detail(
    val noteId: Long) : NavKey

