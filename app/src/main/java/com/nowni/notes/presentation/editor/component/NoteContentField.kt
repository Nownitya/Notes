package com.nowni.notes.presentation.editor.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nowni.notes.ui.theme.NotesTheme

@Composable
fun NoteContentField(
    content:String,
    onContentChange:(String)-> Unit,
    modifier: Modifier = Modifier) {

    OutlinedTextField(
        value = content,
        onValueChange = onContentChange,
        modifier = modifier.fillMaxSize(),
        placeholder = { Text("Content") },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color.Transparent,
//            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NoteContentFieldPreview() {
    NotesTheme{
        Surface{
            NoteContentField(
                content = "This is a sample note content.",
                onContentChange = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}