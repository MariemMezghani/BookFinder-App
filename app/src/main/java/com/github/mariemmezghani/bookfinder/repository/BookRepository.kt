package com.github.mariemmezghani.bookfinder.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.database.BookDao

class BookRepository(private val database: BookDao) {
    var savedBooks: LiveData<List<Book>> = database.getAllBooks()
    suspend fun insert(book: Book) {
        database.insert(book)
    }

    fun getSearchedBooks(query: String): LiveData<List<Book>> {
        return database.getBooks(query)
    }
}