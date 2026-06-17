package com.nowni.notes.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.nowni.notes.core.database.UseCaseProvider
import com.nowni.notes.presentation.detail.DetailScreen
import com.nowni.notes.presentation.detail.state.DetailUiAction
import com.nowni.notes.presentation.detail.state.DetailUiState
import com.nowni.notes.presentation.editor.EditorScreen
import com.nowni.notes.presentation.editor.EditorViewModel
import com.nowni.notes.presentation.editor.EditorViewModelFactory
import com.nowni.notes.presentation.editor.state.EditorUiAction
import com.nowni.notes.presentation.home.HomeScreen
import com.nowni.notes.presentation.home.HomeViewModel
import com.nowni.notes.presentation.home.HomeViewModelFactory
import com.nowni.notes.presentation.home.previewNotes


@Composable
fun AppNavGraph() {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Home)

    var notes by remember { mutableStateOf(previewNotes) }

    val entryProvider: (NavKey) -> NavEntry<NavKey> = entryProvider {
        entry<Home> {

            val context = LocalContext.current
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    getNotesUseCase = UseCaseProvider.provideNotesUseCase(context).getNotes
                )
            )

            val uiState by viewModel.uiState.collectAsState()

            HomeScreen(
                uiState = uiState,
                onAddNote = {
                    backStack.add(Editor())
                },
                onNoteClick = { noteId ->
                    backStack.add(Detail(noteId))
                }
            )
        }

        entry<Editor> { editor ->
            val context = LocalContext.current
            val usecases = UseCaseProvider.provideNotesUseCase(context)

            val viewModel: EditorViewModel = viewModel(
                factory = EditorViewModelFactory(
                    addNoteUseCase = usecases.addNote,
                    updateNoteUseCase = usecases.updateNote,
                    getNoteByIdUseCase = usecases.getNotesById
                )
            )

            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(editor.noteId) {
                editor.noteId?.let {
                    viewModel.loadNote(it)
                }
            }

            EditorScreen(
                uiState = uiState,
                onAction = { action ->
                    when (action) {
                        EditorUiAction.NavigateBack -> {
                            backStack.removeLastOrNull()
                        }

                        EditorUiAction.SaveNote -> {
                            viewModel.saveNote(editor.noteId)
                        }
                        else -> {
                            viewModel.onAction(action)
                        }
                    }

                })

            /*EditorScreen(
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
            )*/

        }
        entry<Detail> { detail ->

            val note = notes.firstOrNull {
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

                        DetailUiAction.DeleteNote -> {
                            notes = notes.filterNot {
                                it.id == detail.noteId
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