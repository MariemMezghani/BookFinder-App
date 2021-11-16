package com.github.mariemmezghani.bookfinder.repository

import androidx.lifecycle.LiveData
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.database.BookDao

class BookRepository(private val database: BookDao) {
    var savedBooks: LiveData<List<Book>> = database.getAllBooks()
    var readBooks: LiveData<List<Book>> = database.getReadBooks()
    var inProgressBooks: LiveData<List<Book>> = database.getInProgressReadBooks()
    var unreadBooks: LiveData<List<Book>> = database.getUnReadBooks()

    suspend fun insert(book: Book) {
        database.insert(book)
    }

    suspend fun update(book: Book) {
        database.update(book)
    }

    fun getSearchedBooks(query: String): LiveData<List<Book>> {
        return database.getBooks(query)
    }

    suspend fun delete(book: Book) {
        return database.delete(book)
    }


}
