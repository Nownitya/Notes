package com.nowni.notes.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nowni.notes.domain.model.Note
import com.nowni.notes.presentation.home.component.NoteCard
import com.nowni.notes.presentation.home.state.HomeUiState
import com.nowni.notes.ui.theme.NotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onAddNote: () -> Unit = {},
    onNoteClick: (Long) -> Unit = {}
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Notes") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = onAddNote) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = "Add Note"
            )
        }
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = uiState.notes,
                key = { it.id })
            { note ->
                NoteCard(
                    note = note,
                    onclick = {
                        onNoteClick(it.id)
                    })
            }
        }
    }

}

val previewNotes = listOf(
    Note(
        id = 1, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 2, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 3, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    ), Note(
        id = 4, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 5, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 6, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    ), Note(
        id = 7, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 8, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 9, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    ), Note(
        id = 10, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 11, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 12, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    ), Note(
        id = 13, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 14, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 15, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    ), Note(
        id = 16, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 17, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 18, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    ), Note(
        id = 19, title = "Shopping List", content = "Buy milk, eggs and bread"
    ), Note(
        id = 20, title = "Interview Preparation", content = "Study Coroutines, Flow and Hilt"
    ), Note(
        id = 21, title = "Project Ideas", content = "Notes App, Expense Tracker and Weather App"
    )
)

@Preview
@Composable
private fun HomeScreenPreview() {
    NotesTheme {
        HomeScreen(
            uiState = HomeUiState(notes = previewNotes),
            onAddNote = {},
            onNoteClick = {}
        )
    }
}