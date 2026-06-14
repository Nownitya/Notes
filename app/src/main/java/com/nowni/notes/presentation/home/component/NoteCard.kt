package com.nowni.notes.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nowni.notes.domain.model.Note
import com.nowni.notes.core.ui.theme.NotesTheme

@Composable
fun NoteCard(
    note: Note,
    onclick: (Note) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onclick(note) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 5,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
    NotesTheme {
        Surface {
            NoteCard(
                note = Note(
                    id = 1,
                    title = "Shopping List",
                    content = "Buy Milk, bread,"
                ),
                onclick = {}
            )
        }
    }

}