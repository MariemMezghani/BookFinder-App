package com.github.mariemmezghani.bookfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.github.mariemmezghani.bookfinder.databinding.FragmentListBinding


class ListFragment : Fragment() {

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
        return binding.root
    }
}