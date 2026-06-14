package com.nowni.notes.presentation.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nowni.notes.presentation.editor.component.NoteContentField
import com.nowni.notes.presentation.editor.component.NoteTitleField
import com.nowni.notes.presentation.editor.state.EditorUiAction
import com.nowni.notes.presentation.editor.state.EditorUiState
import com.nowni.notes.core.ui.theme.NotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    uiState: EditorUiState,
    onAction: (EditorUiAction) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Editor")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(EditorUiAction.NavigateBack) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NoteTitleField(
                title = uiState.title,
                onTitleChange = {
                    onAction(EditorUiAction.TitleChange(it))
                }
            )
            NoteContentField(
                content = uiState.content,
                onContentChange = {
                    onAction(EditorUiAction.ContentChange(it))
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                enabled = uiState.title.isBlank(),
                onClick = { onAction(EditorUiAction.SaveNote) },
                modifier = Modifier.fillMaxWidth(),

            ) {
                Text("Save")
            }
        }
    }
}

@Preview
@Composable
private fun EditorScreenPreview() {
    NotesTheme {
        Surface {
            var title by rememberSaveable { mutableStateOf("") }
            var content by rememberSaveable { mutableStateOf("") }
            EditorScreen(
                uiState = EditorUiState(
                    title= title,
                    content=content
                ),
                onAction = { action ->
                    when (action) {
                        is EditorUiAction.TitleChange -> title = action.title
                        is EditorUiAction.ContentChange -> content = action.content
                        else -> Unit
                    }
                }
            )
        }
    }

}
