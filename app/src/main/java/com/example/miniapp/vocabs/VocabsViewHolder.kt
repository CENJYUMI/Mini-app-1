package com.example.miniapp.vocabs

import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.VocabsLayoutBinding
import com.example.miniapp.vocabs.ClassVocabs

class VocabsViewHolder (private var binding : VocabsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: ClassVocabs){
        binding.txtEnglishWord.text = item.englishWord

    }

}