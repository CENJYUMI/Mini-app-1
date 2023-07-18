package com.example.miniapp.phrases

import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.PhrasesLayoutBinding



class PhrasesViewHolder (private var binding : PhrasesLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: ClassPhrases){
        binding.txtEnglishWord.text = item.englishPhrases

    }
}