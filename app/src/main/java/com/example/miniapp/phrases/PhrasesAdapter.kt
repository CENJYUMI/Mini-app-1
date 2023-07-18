package com.example.miniapp.phrases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.PhrasesLayoutBinding
import com.example.miniapp.phrases.ItemClickListener

class PhrasesAdapter (private val phrases: List<ClassPhrases>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<PhrasesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhrasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhrasesLayoutBinding.inflate(inflater,parent,false)
        return PhrasesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phrases.size
    }

    override fun onBindViewHolder(holder: PhrasesViewHolder, position: Int) {
        val item2 = phrases[position]
        holder.bind(item2)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item2,position)
        }
    }


}