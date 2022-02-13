package com.example.educational3dworld.presenter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.educational3dworld.R
import com.example.educational3dworld.data.models.CollectionData
import com.example.educational3dworld.databinding.ItemModelBinding


class CollectionAdapter : ListAdapter<CollectionData, CollectionAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((Int) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<CollectionData>() {
        override fun areItemsTheSame(oldItem: CollectionData, newItem: CollectionData): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CollectionData, newItem: CollectionData): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemModelBinding::bind)

        init {
            itemView.setOnClickListener {
                itemListener?.invoke(absoluteAdapterPosition)
            }
        }

        fun load() {
            val value = getItem(absoluteAdapterPosition) as CollectionData
            Glide.with(bind.modelImge.context).load(value.image).into(bind.modelImge)
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        )

    fun setListener(f: (Int) -> Unit) {
        itemListener = f
    }

}