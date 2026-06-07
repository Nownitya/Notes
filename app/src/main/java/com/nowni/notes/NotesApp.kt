package com.nowni.notes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nowni.notes.presentation.navigation.AppNavGraph
import com.nowni.notes.ui.theme.NotesTheme

@Composable
fun NotesApp() {
    NotesTheme {
        Surface {
            AppNavGraph()
        }
    }
}
