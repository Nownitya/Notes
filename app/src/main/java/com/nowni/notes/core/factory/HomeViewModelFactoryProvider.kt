package com.nowni.notes.core.factory

import android.content.Context
import com.nowni.notes.core.database.UseCaseProvider
import com.nowni.notes.presentation.home.HomeViewModelFactory

object HomeViewModelFactoryProvider {
    fun provide(context: Context): HomeViewModelFactory {
        val userCases = UseCaseProvider.provideNotesUseCase(context)

        return HomeViewModelFactory(getNotesUseCase = userCases.getNotes)

    }
}