package com.github.mariemmezghani.bookfinder.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * from books_table WHERE bookId = :bookId")
    suspend fun get(bookId: String): Book?

    @Query("SELECT * FROM books_table WHERE name LIKE :query OR author LIKE :query")
    fun getBooks(query: String): LiveData<List<Book>>

    @Query("SELECT * FROM books_table ORDER BY bookId DESC")
    fun getAllBooks(): LiveData<List<Book>>
}