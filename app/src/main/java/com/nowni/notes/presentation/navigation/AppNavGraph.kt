package com.nowni.notes.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.nowni.notes.presentation.detail.DetailScreen
import com.nowni.notes.presentation.editor.EditorScreen
import com.nowni.notes.presentation.home.HomeScreen


@Composable
fun AppNavGraph() {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Home)

    val entryProvider: (NavKey) -> NavEntry<NavKey> = entryProvider {
        entry<Home> {

            HomeScreen()

        }
        entry<Editor>{
            EditorScreen()

        }
        entry <Detail>{
            DetailScreen()

        }


    }
    NavDisplay(
        backStack,
        onBack = {if (backStack.size>1) backStack.removeLastOrNull()},
        entryProvider = entryProvider,
        )
}