package com.github.mariemmezghani.bookfinder.viewModel

import androidx.lifecycle.ViewModel
import com.github.mariemmezghani.bookfinder.repository.BookRepository

class BookViewModel(private val repository: BookRepository): ViewModel() {
    val books=repository.savedBooks
    init {
        getAllBooks()
    }
    fun getAllBooks()= books
}