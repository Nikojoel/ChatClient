package com.example.chatclient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context, private val myData: List<String>) : RecyclerView.Adapter<ItemViewHolder>() {

    // Inflates the itemView
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, vg, false)
        return  ItemViewHolder(itemView)
    }

    // Returns the list size
    override fun getItemCount(): Int {
        return myData.size
    }

    // Sets the textView to value that is found at the index of pos
    override fun onBindViewHolder(vh: ItemViewHolder, pos: Int) {
        vh.itemView.findViewById<TextView>(R.id.messageText).text = myData[pos]
    }
}