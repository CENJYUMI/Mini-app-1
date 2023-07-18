package com.example.miniapp.alphabets

import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.AlphabetsLayoutBinding



class AlphabetsViewHolder(private var binding : AlphabetsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ClassAlphabets) {
        binding.txtEnglishWord.text = item.englishAlphabets
    }
}