package com.antonov.dailynotescalendar.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.domain.model.Item

class RecyclerItemAdapter(
    private val onClickListener: OnItemClickListener,
    private val onLongItemClickListener: OnLongItemClickListener,
    context: Context?,
    items: List<Item>,
) :
    RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(item: Item?, position: Int)
    }

    interface OnLongItemClickListener {
        fun onLongItemClick(item: Item?, position: Int)
    }

    private val inflater: LayoutInflater
    private val items: List<Item>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = holder.adapterPosition
        val item: Item = items[pos]
        holder.textViewName.text = item.name
        holder.textViewSize.text = item.size
        if (item.isChecked)
            holder.cardView.setBackgroundResource(R.color.light_green)
        else
            holder.cardView.setBackgroundResource(R.color.white)
        holder.itemView.setOnClickListener { onClickListener.onItemClick(item, pos) }
        holder.itemView.setOnLongClickListener {
            onLongItemClickListener.onLongItemClick(item, pos)
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewSize: TextView
        val cardView: CardView

        init {
            textViewName = view.findViewById(R.id.textViewName)
            textViewSize = view.findViewById(R.id.textViewSize)
            cardView = view.findViewById(R.id.cardView)
        }
    }

    init {
        this.items = items
        inflater = LayoutInflater.from(context)
    }
}