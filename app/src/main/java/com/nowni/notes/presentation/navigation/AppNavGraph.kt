package com.nowni.notes.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.nowni.notes.presentation.detail.DetailViewModel
import com.nowni.notes.presentation.detail.DetailViewModelFactory
import com.nowni.notes.presentation.detail.state.DetailUiAction
import com.nowni.notes.presentation.editor.EditorScreen
import com.nowni.notes.presentation.editor.EditorViewModel
import com.nowni.notes.presentation.editor.EditorViewModelFactory
import com.nowni.notes.presentation.editor.state.EditorUiAction
import com.nowni.notes.presentation.home.HomeScreen
import com.nowni.notes.presentation.home.HomeViewModel
import com.nowni.notes.presentation.home.HomeViewModelFactory


@Composable
fun AppNavGraph() {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Home)

//    var notes by remember { mutableStateOf(previewNotes) }

    val entryProvider: (NavKey) -> NavEntry<NavKey> = entryProvider {
        entry<Home> {

            val context = LocalContext.current

            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    getNotesUseCase = UseCaseProvider.provideNotesUseCase(context).getNotes
                )
            )

            val uiState by viewModel.uiState.collectAsState()

            HomeScreen(uiState = uiState, onAddNote = {
                backStack.add(Editor())
            }, onNoteClick = { noteId ->
                backStack.add(Detail(noteId))
            })
        }

        entry<Editor> { editor ->
            val context = LocalContext.current
            val useCases = UseCaseProvider.provideNotesUseCase(context)

            val viewModel: EditorViewModel = viewModel(
                factory = EditorViewModelFactory(
                    addNoteUseCase = useCases.addNote,
                    updateNoteUseCase = useCases.updateNote,
                    getNoteByIdUseCase = useCases.getNotesById
                )
            )

            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(editor.noteId) {
                editor.noteId?.let {
                    viewModel.loadNote(it)
                }
            }

            EditorScreen(
                uiState = uiState, onAction = { action ->
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

        }
        entry<Detail> { detail ->

            val context = LocalContext.current

            val useCases = UseCaseProvider.provideNotesUseCase(context)

            val viewModel: DetailViewModel = viewModel(
                factory = DetailViewModelFactory(
                    getNoteByIdUseCase = useCases.getNotesById,
                    deleteNoteUseCase = useCases.deleteNote
                )
            )

            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(detail.noteId) {
                viewModel.loadNote(detail.noteId)
            }

            DetailScreen(
                uiState = uiState, onAction = { action ->
                    when (action) {
                        DetailUiAction.NavigateBack -> {
                            backStack.removeLastOrNull()
                        }

                        DetailUiAction.EditNote -> {
                            backStack.add(
                                Editor(detail.noteId)
                            )
                        }

                        DetailUiAction.DeleteNote -> {
                            viewModel.deleteNote()
                            backStack.removeLastOrNull()
                        }
                    }
                })
        }


    }
    NavDisplay(
        backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryProvider = entryProvider,
    )
}