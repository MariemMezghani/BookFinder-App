package com.github.mariemmezghani.bookfinder.repository

import androidx.lifecycle.LiveData
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.database.BookDao

class BookRepository(private val database: BookDao) {
    var savedBooks: LiveData<List<Book>> = database.getAllBooks()
}