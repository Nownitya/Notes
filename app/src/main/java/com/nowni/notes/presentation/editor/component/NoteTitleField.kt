package com.nowni.notes.presentation.editor.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun NoteTitleField(
    title: String, onTitleChange: (String) -> Unit, modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        modifier = modifier,
        label = {
            Text("Title")
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.headlineMedium,
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
