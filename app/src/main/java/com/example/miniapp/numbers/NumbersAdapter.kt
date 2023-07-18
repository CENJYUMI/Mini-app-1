package com.example.miniapp.numbers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.NumLayoutBinding
import com.example.miniapp.numbers.ItemClickListener


class NumbersAdapter (private val numbers: List<ClassNumbers>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<NumbersViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NumLayoutBinding.inflate(inflater,parent,false)
        return NumbersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        val item4 = numbers[position]
        holder.bind(item4)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item4,position)
        }
    }

}