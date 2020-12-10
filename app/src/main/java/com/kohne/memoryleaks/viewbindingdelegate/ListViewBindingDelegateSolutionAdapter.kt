package com.kohne.memoryleaks.viewbindingdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kohne.memoryleaks.databinding.ListItemBinding

internal class ListViewBindingDelegateSolutionAdapter(
    private val listener: OnListViewBindingItemClickedListener
) :
    RecyclerView.Adapter<ListViewBindingDelegateSolutionAdapter.ListViewBindingViewHolder>() {

    private val items = listOf("A", "B", "C", "D", "E", "F", "G")

    interface OnListViewBindingItemClickedListener {
        fun onListItemClicked(data: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewBindingViewHolder {
        return ListViewBindingViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewBindingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ListViewBindingViewHolder(
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