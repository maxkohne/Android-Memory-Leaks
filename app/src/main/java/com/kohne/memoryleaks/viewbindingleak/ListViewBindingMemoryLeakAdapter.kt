package com.kohne.memoryleaks.viewbindingleak

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kohne.memoryleaks.databinding.ListItemBinding

internal class ListViewBindingMemoryLeakAdapter(
    private val listener: OnListMemoryLeakItemClickedListener
) :
    RecyclerView.Adapter<ListViewBindingMemoryLeakAdapter.ListMemoryLeakViewHolder>() {

    private val items = listOf("A", "B", "C", "D", "E", "F", "G")

    interface OnListMemoryLeakItemClickedListener {
        fun onListItemClicked(data: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMemoryLeakViewHolder {
        return ListMemoryLeakViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListMemoryLeakViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ListMemoryLeakViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onListItemClicked(items[position])
                }
            }
        }

        fun bind(data: String) {
            binding.detailsViewBindingTextView.text = data
        }
    }
}