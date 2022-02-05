package com.example.guest.view.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guest.R
import com.example.guest.service.model.GuestModel

class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        tvName.text = guest.name
    }

}