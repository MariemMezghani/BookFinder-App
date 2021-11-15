package com.github.mariemmezghani.bookfinder

import android.os.Bundle
import android.view.*
import android.widget.Filter
import android.widget.Filter.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.github.mariemmezghani.bookfinder.database.BookDao
import com.github.mariemmezghani.bookfinder.database.BookDatabase
import com.github.mariemmezghani.bookfinder.databinding.FragmentListBinding
import com.github.mariemmezghani.bookfinder.utils.Injection
import com.github.mariemmezghani.bookfinder.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*


class ListFragment : Fragment() {

    //private lateinit var viewModel: BookViewModel
    // viewModel
    val viewModel: BookViewModel by activityViewModels {
        Injection.provideViewModelFactory(getDatabase())
    }
    val adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "My Reading List"
        binding.addButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }


        binding.viewModel = viewModel

        // adapter
        binding.recyclerview.adapter = adapter
        viewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        // specify the current activity as the current lifecycleowner of the binding.
        // This is necessary so that the binding can observe LiveData updates
        binding.setLifecycleOwner(this)
        // menu
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun getDatabase(): BookDao {
        // database
        val application = requireNotNull(this.activity).application
        val database = BookDatabase.getInstance(application).bookDao
        return database
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.searchText)
        // convert searchItem in menu to SearchView Widget
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        // enables submit button in the SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //will be triggered only when we tap the search button
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    // make sure the recycler view scrolls to position 0
                    //binding.recyclerview.scrollToPosition(0)
                    searchBooks(query)

                    //remove keyboard
                    searchView.clearFocus()
                }
                return true
            }

            // this function will be triggered when we write a charachter in the search view
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchBooks(query)
                }
                return true
            }
        })
    }

    private fun searchBooks(query: String) {

        viewModel.searchBooksList(query).observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

    }

}