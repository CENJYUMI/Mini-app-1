package com.example.miniapp.vocabs

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.VocabsLayoutBinding

class VocabsAdapter(private val vocabs: List<ClassVocabs>, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<VocabsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VocabsLayoutBinding.inflate(inflater, parent, false)
        return VocabsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vocabs.size
    }

    override fun onBindViewHolder(holder: VocabsViewHolder, position: Int) {
        val item = vocabs[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item, position)
        }
    }
}

//        holder.bind(vocabs[position])
//        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context,vocabs[position].englishWord,Toast.LENGTH_SHORT).show()
//            var myIntent = Intent(holder.itemView.context, VocabsInfoActivity::class.java)
//            myIntent.putExtra("english", vocabs[position].englishWord)
//            myIntent.putExtra("korean", vocabs[position].koreanWord)
//            myIntent.putExtra("roman", vocabs[position].romanization)
//            myIntent.putExtra("unit", vocabs[position].unit)
//            myIntent.putExtra("koreanSentence", vocabs[position].sentenceKorean)
//            myIntent.putExtra("englishSentence", vocabs[position].sentenceEnglish)
//            holder.itemView.context.startActivities(arrayOf(myIntent))
//        }
