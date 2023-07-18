package com.example.miniapp.alphabets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.AlphabetsLayoutBinding


class AlphabetAdapter(private val alphabets: List<ClassAlphabets>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<AlphabetsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlphabetsLayoutBinding.inflate(inflater,parent,false)
        return AlphabetsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return alphabets.size
    }

    override fun onBindViewHolder(holder: AlphabetsViewHolder, position: Int) {
        val item5 = alphabets[position]
        holder.bind(item5)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item5,position)
        }
    }

}