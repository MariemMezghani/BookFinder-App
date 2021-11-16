package com.github.mariemmezghani.bookfinder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.mariemmezghani.bookfinder.database.Book
import com.github.mariemmezghani.bookfinder.databinding.BookListItemBinding

class BookAdapter(val listener: BookListener) :
    androidx.recyclerview.widget.ListAdapter<Book, BookAdapter.ViewHolder>(ProductDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listener, getItem(position)!!)
    }


    class ViewHolder private constructor(val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(listener: BookListener, item: Book) {
            binding.book = item
            binding.listener = listener
            if (item.read) {
                binding.status.text = "Read"
                binding.status.setTextColor(Color.parseColor("#73C088"))
            } else if (item.unread) {
                binding.status.text = "Unread"
                binding.status.setTextColor(Color.parseColor("#7f0000"))
            } else if (item.inProgress) {
                binding.status.text = "In Progress"
                binding.status.setTextColor(Color.parseColor("#FFBB86FC"))
            }

            binding.executePendingBindings()

        }
    }
}

class BookListener(val clickListener: (book: Book) -> Unit) {
    fun onClick(book: Book) = clickListener(book)
}

class ProductDiffUtil : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.bookId == newItem.bookId
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}