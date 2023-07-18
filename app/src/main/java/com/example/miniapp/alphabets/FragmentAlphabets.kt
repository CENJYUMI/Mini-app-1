package com.example.miniapp.alphabets

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.R
import com.example.miniapp.databinding.FragmentAlphabetsBinding
//import com.example.miniapp.alphabets.ItemClickListener
import java.util.Locale

interface ItemClickListener {
    fun onItemClick(item: ClassAlphabets, position: Int)
}

class FragmentAlphabets : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentAlphabetsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlphabetsBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.AlphabetsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Language not supported by TTS engine

                    tts.setSpeechRate(0.5f)
                }
            } else {
                // TTS engine initialization failed
            }
        }
        val koreanWords = listOf(
            //Consonants
            ClassAlphabets("Consonants - g/k", "ㄱ", "Name of the consonant \n기역 / giyeok"),
            ClassAlphabets("Consonants - n/n", "ㄴ", "Name of the consonant \n니은 / nieun"),
            ClassAlphabets("Consonants - d/t", "ㄷ", "Name of the consonant \n디귿 / digeut"),
            ClassAlphabets("Consonants - r/l", "ㄹ  ", "Name of the consonant \n리을 / rieul"),
            ClassAlphabets("Consonants - m/m", "ㅁ  ", "Name of the consonant \n미음 / mieum"),
            ClassAlphabets("Consonants - b/p", "ㅂ ", "Name of the consonant \n비읍 / bieup"),
            ClassAlphabets("Consonants - s/t", "ㅅ ", "Name of the consonant \n시옷 / siot"),
            ClassAlphabets("Consonants - silent/ng", "ㅇ   ", "Name of the consonant \n이응 / ieung"),
            ClassAlphabets("Consonants - j/t", "ㅈ  ", "Name of the consonant \n지읒 / jieut"),
            ClassAlphabets("Consonants - ch/t", "ㅊ  ", "Name of the consonant \n치읓 / chieut"),
            ClassAlphabets("Consonants -k/k", "ㅋ  ", "Name of the consonant \n키읔 / kieuk"),
            ClassAlphabets("Consonants - t/t", "ㅌ  ", "Name of the consonant \n티읕 / tieut"),
            ClassAlphabets("Consonants -p/p", "ㅍ  ", "Name of the consonant \n피읖 / pieup"),
            ClassAlphabets("Consonants -h/t", "ㅎ ", "Name of the consonant \n히읗 / hieut"),
            //tense consonants
            ClassAlphabets("Tense Consonants - kk/k ", "ㄲ ", "Name of the consonant \n쌍기역 / ssangiyeok"),
            ClassAlphabets("Tense Consonants - tt", "ㄸ", "Name of the consonant \n쌍디귿 / ssangdigeut"),
            ClassAlphabets("Tense Consonants - pp", " ㅃ ", "Name of the consonant \n쌍비읍 / ssangbieup"),
            ClassAlphabets("Tense Consonants - jj", "ㅉ ", "Name of the consonant \n쌍지읒 / ssangjieut"),
            ClassAlphabets("Tense Consonants - ss/t", "ㅆ", "Name of the consonant \n쌍시옷 / ssangsiot"),
            //Vowels
            ClassAlphabets("Vowels -a ", "ㅏ ", "a"),
            ClassAlphabets("Vowels - ya", "ㅑ  ", "ya"),
            ClassAlphabets("Vowels - eo", "ㅓ ", "eo"),
            ClassAlphabets("Vowels - yeo", "ㅕ ", "yeo"),
            ClassAlphabets("Vowels - o", "ㅗ ", "o"),
            ClassAlphabets("Vowels - yo", " ㅛ ", "yo"),
            ClassAlphabets("Vowels - u", "ㅜ ", "u"),
            ClassAlphabets("Vowels - yu", "ㅠ ", "yeong"),
            ClassAlphabets("Vowels - eu", " ㅡ ", "yeong"),
            ClassAlphabets("Vowels - i", "ㅣ", "yeong"),
            //complex vowels
            ClassAlphabets("Complex Vowels - ui", "ㅢ ", ""),
            ClassAlphabets("Complex Vowels - oe", "ㅚ ", ""),
            ClassAlphabets("Complex Vowels - ae", "ㅐ ", ""),
            ClassAlphabets("Complex Vowels - wi", "ㅟ ", ""),
            ClassAlphabets("Complex Vowels - e ", "ㅔ ", ""),
            ClassAlphabets("Complex Vowels - yae", "ㅒ ", ""),
            ClassAlphabets("Complex Vowels - ye", "ㅖ ", ""),
            ClassAlphabets("Complex Vowels - wa", "ㅘ ", ""),
            ClassAlphabets("Complex Vowels - wo ", "ㅝ ", ""),
            ClassAlphabets("Complex Vowels - wae", "ㅙ", ""),
            ClassAlphabets("Complex Vowels - we", "ㅞ", ""),



        )
        recyclerView.adapter = AlphabetAdapter(koreanWords, this)
        return view
    }

    override fun onItemClick(item: ClassAlphabets, position: Int) {
        val speechRate = 0.5f
        val koreanWord = item.koreanAlphabets


        when {
            !tts.isSpeaking -> {

                tts.speak(koreanWord, TextToSpeech.QUEUE_ADD, null, "koreanWord")

                tts.setSpeechRate(speechRate)
            }

            else -> {
                tts.stop()

                tts.speak(koreanWord, TextToSpeech.QUEUE_ADD, null, "koreanWord")

                tts.setSpeechRate(speechRate)
            }
        }
        val intent = Intent(requireContext(), AlphabetsInfoActivity::class.java)
        intent.putExtra("englishA", item.englishAlphabets)
        intent.putExtra("koreanA", item.koreanAlphabets)
        intent.putExtra("romanA", item.RomanNum)

        requireContext().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Shutdown Text-to-Speech engine
        tts.shutdown()
    }
}


