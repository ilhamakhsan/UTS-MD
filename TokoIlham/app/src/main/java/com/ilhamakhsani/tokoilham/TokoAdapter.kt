package com.ilhamakhsani.Tokoilham

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilhamakhsani.Tokoilham.model.Toko

class TokoAdapter (
    private val listItems: ArrayList<Toko>,
    private val listener: TokoListener
    ) : RecyclerView.Adapter<TokoAdapter.TokoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_toko, parent, false)
            return TokoViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listItems.size
        }

        override fun onBindViewHolder(holder: TokoViewHolder, position: Int) {
            val item = listItems[position]
            holder.textViewTitle.text = item.title
            holder.textViewBody.text = item.body
            holder.itemView.setOnClickListener {
                listener.OnItemClicked(item)
            }
        }

        class TokoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
            var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
        }

        interface TokoListener{
            fun OnItemClicked(Toko: Toko)
        }
    }
