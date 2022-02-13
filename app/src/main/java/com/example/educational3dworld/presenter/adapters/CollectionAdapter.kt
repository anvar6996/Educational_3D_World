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
import com.example.educational3dworld.data.models.ObjectData
import com.example.educational3dworld.databinding.ItemModelBinding


class CollectionAdapter : ListAdapter<ObjectData, CollectionAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((Int) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<ObjectData>() {
        override fun areItemsTheSame(oldItem: ObjectData, newItem: ObjectData): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ObjectData, newItem: ObjectData): Boolean {
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
            val value = getItem(absoluteAdapterPosition) as ObjectData
            bind.textModel.text = value.name
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