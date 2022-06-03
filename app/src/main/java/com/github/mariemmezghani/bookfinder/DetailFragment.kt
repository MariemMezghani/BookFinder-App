package com.github.mariemmezghani.bookfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.database.BookDatabase
import com.github.mariemmezghani.bookfinder.databinding.FragmentDetailBinding
import com.github.mariemmezghani.bookfinder.utils.DateInputMask
import com.github.mariemmezghani.bookfinder.utils.Injection
import com.github.mariemmezghani.bookfinder.viewModel.BookViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {
    private lateinit var book: Book
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
        val args = DetailFragmentArgs.fromBundle(requireArguments()).book
        if (args == null) {
            book = Book(name = "")
        } else {
            book = args
        }

        // load ads
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

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
        //Snackbar
        viewModel.showSnackBar.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "enter book name",
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                viewModel.doneShowingSnackBar()
            }
        })
        // edittext date
        DateInputMask(binding.publishedDateEdittext)

        return binding.root
    }

}