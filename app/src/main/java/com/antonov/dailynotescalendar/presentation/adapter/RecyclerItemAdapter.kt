package com.antonov.dailynotescalendar.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.domain.model.Hour
import com.antonov.dailynotescalendar.domain.model.Note

class RecyclerItemAdapter(private val items: List<Hour>, private val itemLayout: Int, private val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = items[position]
        holder.itemView.setOnClickListener { itemClickListener.onItemClick(item, position) }
        holder.updateNote(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ItemViewHolder(view)
    }
}

class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private var textViewName: TextView = itemView.findViewById(R.id.textViewName)
    private var textViewSize: TextView = itemView.findViewById(R.id.textViewSize)


    fun updateNote(item: Hour) {
        textViewName.text = item.hours
        textViewSize.text = item.note?.name ?: "-"
    }
}

interface OnItemClickListener {
    fun onItemClick(item: Hour?, position: Int)
}