package com.example.myappstore.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myappstore.database.DbEntry
import com.example.myappstore.databinding.GridViewItemBinding


class AppListAdapter(val listener: PhotoOnclickListener) :
    ListAdapter<DbEntry, AppListAdapter.AppDataViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppDataViewHolder {
        return AppDataViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AppDataViewHolder, position: Int) {
        val entry = getItem(position)
        holder.itemView.setOnClickListener {

            listener.onClick(entry)
        }

        holder.bind(entry)
    }


    class AppDataViewHolder(val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: DbEntry) {

            binding.viewModel = entry
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<DbEntry>() {
        override fun areItemsTheSame(oldItem: DbEntry, newItem: DbEntry): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DbEntry, newItem: DbEntry): Boolean {
            return oldItem.id == newItem.id
        }


    }


    class PhotoOnclickListener(val clickListener: (entry: DbEntry) -> Unit) {

        fun onClick(entry: DbEntry) = clickListener(entry)
    }

}

