package com.github.mariemmezghani.bookfinder.utils

import androidx.lifecycle.ViewModelProvider
import com.github.mariemmezghani.bookfinder.database.BookDao
import com.github.mariemmezghani.bookfinder.repository.BookRepository
import com.github.mariemmezghani.bookfinder.viewModel.BookViewModelFactory

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */

object Injection {
    /**
     * Creates an instance of [BookRepository] based on the [BookDatabase]
     */
    fun provideBookRepository(bookdb: BookDao): BookRepository {
        return BookRepository(bookdb)
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [BookViewModel] objects.
     */
    fun provideViewModelFactory(bookdb: BookDao): ViewModelProvider.Factory {
        return BookViewModelFactory(provideBookRepository(bookdb))
    }
}
