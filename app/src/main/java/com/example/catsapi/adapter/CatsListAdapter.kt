package com.example.catsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapi.databinding.CatItemBinding
import com.example.catsapi.model.Cat
import com.example.catsapi.ui.CardViewClickListener
import com.example.catsapi.utils.setImageByGlide

class CatsListAdapter(private val listener: CardViewClickListener) :
    PagingDataAdapter<Cat, CatViewHolder>(CatDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class CatViewHolder(
    private val binding: CatItemBinding,
    private val listener: CardViewClickListener,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: Cat) = binding.run {
        catImage.setImageByGlide(cat.imageUrl)
        initButtons(cat)
    }

    private fun initButtons(cat: Cat) = binding.run {
        cardView.setOnClickListener {
            listener.onCardViewClickListener(cat)
        }
    }
}

class CatDiffItemCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }
}
