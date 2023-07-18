package com.example.miniapp.phrases

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.R
import com.example.miniapp.databinding.FragmentPhrases2Binding

import java.util.Locale

interface ItemClickListener {
    fun onItemClick(item: ClassPhrases, position: Int)
}

class FragmentPhrases : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentPhrases2Binding
    private lateinit var recyclerView: RecyclerView
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhrases2Binding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.PhrasesRecyclerView
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
            ClassPhrases("honey, sweetheart", "여보", "yeo-bo"),
            ClassPhrases("It can’t be …", "안돼", "An-dwae"),
            ClassPhrases("no", "아니요", "A-ni-yo"),
            ClassPhrases("got it or understood", "알았어", "Ar-ass-eo"),
            ClassPhrases("Are you hungry?", "배고파", "Bae-go-pah?"),
            ClassPhrases("I miss you", "보고싶다", "Bogo-shipda"),
            ClassPhrases("Really?", "진짜", "Chin-chha"),
            ClassPhrases("How/What to do?", "어떻게", "Eo-tto-ke"),
            ClassPhrases("Don’t worry", "걱정하지마", "Geok-jung-ha-ji-ma"),
            ClassPhrases("Sure/ If that’s the case, then …", "그럼", "Geu-reom"),
            ClassPhrases("Are you okay?", "괜찮아?", "Gwen-cha-na?"),
            ClassPhrases("Don’t do this…", "하지마", "Ha-ji-ma"),
            ClassPhrases("I’m happy", "행복해", "Heng-bok-hae"),
            ClassPhrases("By any chance…", "혹시", "Heok-shi"),
            ClassPhrases("Please", "잘자", "Jal-ja"),
            ClassPhrases("Sleep well", "괜찮아?", "Gwen-cha-na?"),
            ClassPhrases("Hold on a second", "잠깐만", "Jam-ka-mann"),
            ClassPhrases("excuse me", "저기요", "Jeo-gi-yo"),
            ClassPhrases("I like you", "좋아해", "Jo-ah-hae"),
            ClassPhrases("sorry", "죄송합니다", "Joe-song-ham-ni-da"),
            ClassPhrases("please give me", "주세요", "Ju-se-yo"),
            ClassPhrases("used when scared or surprised", "깜짝이야", "Kamjagiya"),
            ClassPhrases("Don’t leave", "가지마", "Ka-ji-ma"),
            ClassPhrases("I love you", "사랑해", "Sa-rang-hae"),
            ClassPhrases("What’s wrong?", "왜그래", "Wae-geu-rae?"),
            ClassPhrases("Promise me", "약속해", "Yak-sohk-hae"),
            ClassPhrases("You looks pretty", "예쁘다", "Ye-ppeu-da"),
            ClassPhrases("Thank you", "감사합니다", "kam-sa-ham-ni-da"),
            ClassPhrases("Don’t lie", "거짓말 하지마", "geo-jin-mal ha-ji-ma"),
            ClassPhrases("It’s a lie", "거짓말이야", "geo-jin-mar-i-ya"),
            ClassPhrases("Sure", "그럼", "geu-reom"),
            ClassPhrases("Deal", "콜", "kol"),





            )
        recyclerView.adapter = PhrasesAdapter(koreanWords, this)
        return view
    }

    override fun onItemClick(item: ClassPhrases, position: Int) {
        val speechRate = 0.5f
        val koreanWord = item.koreanPhrases


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
        val intent = Intent(requireContext(), PhrasesInfoActivity::class.java)
        intent.putExtra("englishP", item.englishPhrases)
        intent.putExtra("koreanP", item.koreanPhrases)
        intent.putExtra("romanP", item.phrasesRoman)

        requireContext().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Shutdown Text-to-Speech engine
        tts.shutdown()
    }
}
