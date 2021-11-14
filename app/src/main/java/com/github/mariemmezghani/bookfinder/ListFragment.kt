package com.github.mariemmezghani.bookfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.github.mariemmezghani.bookfinder.database.BookDatabase
import com.github.mariemmezghani.bookfinder.databinding.FragmentListBinding
import com.github.mariemmezghani.bookfinder.utils.Injection
import com.github.mariemmezghani.bookfinder.viewModel.BookViewModel
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {

    private lateinit var viewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.toolbar.title = "My Reading List"
        binding.addButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }
        // database
        val application = requireNotNull(this.activity).application
        val database = BookDatabase.getInstance(application).bookDao

        // viewModel
        val viewModel: BookViewModel by activityViewModels {
            Injection.provideViewModelFactory(database)
        }
        binding.viewModel = viewModel

        // adapter
        val adapter = BookAdapter()
        binding.recyclerview.adapter = adapter
        viewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        // specify the current activity as the current lifecycleowner of the binding.
        // This is necessary so that the binding can observe LiveData updates
        binding.setLifecycleOwner(this)
        return binding.root
    }
}