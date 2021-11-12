package com.github.mariemmezghani.bookfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.database.BookDatabase
import com.github.mariemmezghani.bookfinder.databinding.FragmentDetailBinding
import com.github.mariemmezghani.bookfinder.utils.Injection
import com.github.mariemmezghani.bookfinder.viewModel.BookViewModel
import com.github.mariemmezghani.bookfinder.viewModel.BookViewModelFactory


class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        // database
        val application = requireNotNull(this.activity).application
        val database = BookDatabase.getInstance(application).bookDao

        // viewModel
        val viewModel: BookViewModel by activityViewModels {
            Injection.provideViewModelFactory(database)
        }
        // binding
        val book = Book(name = "")
        binding.book = book
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        viewModel.navigateToListFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change
                viewModel.doneNavigating()
            }
        })
        viewModel.cancelNavigation.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
                viewModel.navigationCancelled()
            }
        })

        return binding.root
    }


}