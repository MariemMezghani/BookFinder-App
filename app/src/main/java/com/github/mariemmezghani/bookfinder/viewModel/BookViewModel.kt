package com.github.mariemmezghani.bookfinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.repository.BookRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class BookViewModel(private val repository: BookRepository): ViewModel() {
    val books=repository.savedBooks
    val navigateToListFragment : LiveData<Boolean>
        get()= _navigateToListFragment

    private var _navigateToListFragment=MutableLiveData<Boolean>()
    private val _cancelNavigation = MutableLiveData<Boolean>()

    val cancelNavigation: LiveData<Boolean>
        get() = _cancelNavigation
    init {
        getAllBooks()
    }
    fun getAllBooks()= books
    fun onAddProduct(book: Book){
        viewModelScope.launch{
            repository.insert(book)
            _navigateToListFragment.value=true
        }

    }
    fun doneNavigating(){
        _navigateToListFragment.value = false
    }
    fun onCancel() {
        _cancelNavigation.value = true
    }
    fun navigationCancelled() {
        _cancelNavigation.value = false
    }


}