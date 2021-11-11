package com.github.mariemmezghani.bookfinder.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * from books_table WHERE name = :book")
    suspend fun get(book: String): Book?

    @Query("SELECT * FROM books_table ORDER BY bookId DESC")
    fun getAllBooks(): LiveData<List<Book>>
}