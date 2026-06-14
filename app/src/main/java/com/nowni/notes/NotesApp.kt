package com.nowni.notes

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.nowni.notes.presentation.navigation.AppNavGraph
import com.nowni.notes.core.ui.theme.NotesTheme

@Composable
fun NotesApp() {
    NotesTheme {
        Surface {
            AppNavGraph()
        }
    }
}
