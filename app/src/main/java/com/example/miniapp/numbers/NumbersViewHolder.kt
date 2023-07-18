package com.example.miniapp.numbers

import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.NumLayoutBinding

class NumbersViewHolder (private var binding : NumLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind (item: ClassNumbers){
        binding.txtEnglishWord.text = item.englishNum
    }

}