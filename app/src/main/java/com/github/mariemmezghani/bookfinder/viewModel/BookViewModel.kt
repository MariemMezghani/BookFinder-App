package com.github.mariemmezghani.bookfinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.repository.BookRepository
import kotlinx.coroutines.launch


class BookViewModel(private val repository: BookRepository) : ViewModel() {
    var books = repository.savedBooks
    var readBooks = repository.readBooks
    var inProgressBooks = repository.inProgressBooks
    var unreadBooks = repository.unreadBooks

    val navigateToListFragment: LiveData<Boolean>
        get() = _navigateToListFragment

    private var _navigateToListFragment = MutableLiveData<Boolean>()
    private val _cancelNavigation = MutableLiveData<Boolean>()

    val cancelNavigation: LiveData<Boolean>
        get() = _cancelNavigation

    val showSnackBar: LiveData<Boolean>
        get() = _showSnackBar
    private var _showSnackBar = MutableLiveData<Boolean>()
    private val _navigateFromBook = MutableLiveData<Book>()
    val navigateFromBook
        get() = _navigateFromBook

    fun onSaveBook(book: Book) {
        if (book.author == "") {
            book.author = "unknown author"
        }
        if (checkDate(book.publishedDate) == false) {
            book.publishedDate = ""
        }


        viewModelScope.launch {
            if (book.name == "") {
                _showSnackBar.value = true
            } else if (_navigateFromBook.value != null) {
                repository.update(book)
                _navigateToListFragment.value = true
            } else {
                repository.insert(book)
                _navigateToListFragment.value = true
            }

        }
    }

    fun searchBooksList(query: String): LiveData<List<Book>> {
        return repository.getSearchedBooks(query)
    }

    fun doneNavigating() {
        _navigateToListFragment.value = false
    }

    fun onCancel() {
        _cancelNavigation.value = true
    }

    fun navigationCancelled() {
        _cancelNavigation.value = false
    }

    fun doneShowingSnackBar() {
        _showSnackBar.value = false
    }

    fun onBookClicked(book: Book) {
        _navigateFromBook.value = book
    }

    fun onBookNavigated() {
        _navigateFromBook.value = null
    }

    fun onBookSwiped(book: Book) = viewModelScope.launch {
        repository.delete(book)
    }

    fun checkDate(string: String): Boolean {
        for (char in string) {
            if ((char.isDigit() || char == '/') == false) {
                return false
            }
        }
        return true
    }


}