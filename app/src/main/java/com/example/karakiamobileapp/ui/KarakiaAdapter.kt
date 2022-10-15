package com.example.karakiamobileapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.karakiamobileapp.data.Karakia
import com.example.karakiamobileapp.databinding.FragmentKarakiaBinding
import com.example.karakiamobileapp.databinding.FragmentKarakiaGalleryBinding


class KarakiaAdapter (private val listener: OnItemClickListener
    ) : ListAdapter<Karakia, KarakiaAdapter.KarakiaViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KarakiaViewHolder {
        val binding = FragmentKarakiaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KarakiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KarakiaViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class KarakiaViewHolder(private val binding: FragmentKarakiaBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener() {
                    val position = adapterPosition
                    val karakia = getItem(position)
                    listener.onItemClick(karakia)
                }
            }
        }


        fun bind(karakia: Karakia) {
            binding.apply {
                videoPreview.setImageResource(karakia.imageResource)
                videoTitle.text = karakia.title
                shortDescription.text = karakia.shortDescription
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick (karakia: Karakia)
    }

    class DiffCallback : DiffUtil.ItemCallback<Karakia>() { //detect changes between items
        override fun areItemsTheSame(oldItem: Karakia, newItem: Karakia) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Karakia, newItem: Karakia) =
            oldItem == newItem

    }
}