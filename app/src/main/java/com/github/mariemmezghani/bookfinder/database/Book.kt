package com.github.mariemmezghani.bookfinder.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "books_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var bookId: Long = 0L,
    @ColumnInfo
    var name:String = "",
    @ColumnInfo
    var author: String = "",
    @ColumnInfo
    var publishedDate: String = "",
    @ColumnInfo
    var read: Boolean=false,
    @ColumnInfo
    var unread: Boolean=true,
    @ColumnInfo
    var inProgress: Boolean=false,
    /* In the future we will implement the feature that allows the
     user to save an image of the book from his phone in the app*/
    /*var image: String = "path Uri"*/
):Parcelable