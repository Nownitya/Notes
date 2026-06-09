package com.nowni.notes.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.nowni.notes.domain.model.Note
import com.nowni.notes.presentation.detail.DetailScreen
import com.nowni.notes.presentation.detail.state.DetailUiAction
import com.nowni.notes.presentation.detail.state.DetailUiState
import com.nowni.notes.presentation.editor.EditorScreen
import com.nowni.notes.presentation.editor.state.EditorUiAction
import com.nowni.notes.presentation.editor.state.EditorUiState
import com.nowni.notes.presentation.home.HomeScreen
import com.nowni.notes.presentation.home.previewNotes
import com.nowni.notes.presentation.home.state.HomeUiState


@Composable
fun AppNavGraph() {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Home)

    var notes by remember { mutableStateOf(previewNotes) }

    val entryProvider: (NavKey) -> NavEntry<NavKey> = entryProvider {
        entry<Home> {

            HomeScreen(
                uiState = HomeUiState(
                    notes = notes
                ),
                onAddNote = {
                    backStack.add(Editor())

                },
                onNoteClick = { noteId ->
                    backStack.add(Detail(noteId))

                }
            )

        }
        entry<Editor> { editor ->

            val existingNote = notes.firstOrNull {
                it.id == editor.noteId
            }
            var uiState by remember(editor.noteId) {
                mutableStateOf(
                    EditorUiState(
                        title = existingNote?.title.orEmpty(),
                        content = existingNote?.content.orEmpty()
                    )
                )
            }
            EditorScreen(

                uiState = uiState,
                onAction = { action ->
                    when (action) {
                        is EditorUiAction.TitleChange -> {
                            uiState = uiState.copy(
                                title = action.title
                            )
                        }

                        is EditorUiAction.ContentChange -> {
                            uiState = uiState.copy(
                                content = action.content
                            )
                        }

                        EditorUiAction.SaveNote -> {
                            if (uiState.title.isBlank()) return@EditorScreen

                            if (editor.noteId == null) {
                                val newNote = Note(
                                    id = (notes.maxOfOrNull { it.id } ?: 0L) + 1,
                                    title = uiState.title,
                                    content = uiState.content
                                )
                                notes = listOf(newNote) + notes
                            } else {
                                notes = notes.map { note ->
                                    if (note.id == editor.noteId) {
                                        note.copy(
                                            title = uiState.title,
                                            content = uiState.content
                                        )
                                    } else {
                                        note
                                    }
                                }
                            }
                            backStack.removeLastOrNull()


                            // PlaceHolder
                        }

                        EditorUiAction.NavigateBack -> backStack.removeLastOrNull()
                    }
                },
            )

        }
        entry<Detail> { detail ->

            val note = notes.firstOrNull{
                it.id == detail.noteId
            }
            DetailScreen(
                uiState = DetailUiState(
                    noteId = detail.noteId,
                    title = note?.title.orEmpty(),
                    content = note?.content.orEmpty()
                ),
                onAction = { action ->
                    when (action) {
                        DetailUiAction.NavigateBack -> {
                            backStack.removeLastOrNull()
                        }

                        DetailUiAction.EditNote -> {
                            backStack.add(Editor(detail.noteId))
                        }
                        DetailUiAction.DeleteNote->{
                            notes= notes.filterNot {
                                it.id== detail.noteId
                            }
                            backStack.removeLastOrNull()
                        }
                    }

                }
            )

        }


    }
    NavDisplay(
        backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryProvider = entryProvider,
    )
}