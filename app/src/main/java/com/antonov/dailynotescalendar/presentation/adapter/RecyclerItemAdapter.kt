package com.antonov.dailynotescalendar.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.domain.model.Note

class RecyclerItemAdapter(private val items: List<Note>, private val itemLayout: Int, private val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = items[position]
        holder.updateCatFact(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ItemViewHolder(view)
    }
}

class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private var textViewName: TextView = itemView.findViewById(R.id.textViewName)
    private var textViewSize: TextView = itemView.findViewById(R.id.textViewSize)


    fun updateCatFact(item: Note, clickListener: OnItemClickListener) {
        textViewName.text = item.id.toString()
        textViewSize.text = item.description
    }
}

interface OnItemClickListener {
    fun onItemClick(item: Note?, position: Int)
}

interface OnLongItemClickListener {
    fun onLongItemClick(item: Note?, position: Int)
}