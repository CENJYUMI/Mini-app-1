package com.example.miniapp.numbers

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
import com.example.miniapp.databinding.FragmentNumberBinding
import java.util.Locale

interface ItemClickListener {
    fun onItemClick(item: ClassNumbers, position: Int)
}

class FragmentNumber : Fragment(), ItemClickListener {
    private lateinit var binding : FragmentNumberBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var tts: TextToSpeech


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNumberBinding.inflate(inflater, container, false  )
        val view = binding.root

        recyclerView= binding.NumbersRecyclerView
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
            ClassNumbers("0", "영 ", "yeong"),
            ClassNumbers("1", "일 / 하나 ", "il / ha-na"),
            ClassNumbers("2", "이 / 둘", "i / dul"),
            ClassNumbers("3", "삼 / 셋", "sam / set"),
            ClassNumbers("4", "사 / 넷", "sa / net"),
            ClassNumbers("5", "오 / 다섯", "o / da-seot"),
            ClassNumbers("6", "육 / 여섯", "yuk / yeo-seot"),
            ClassNumbers("7", "칠 / 일곱", "chil / il-gop"),
            ClassNumbers("8", "팔 / 여덟", "pal / yeo-deol"),
            ClassNumbers("9", "구 / 아홉", "gu / a-hop"),
            ClassNumbers("10", "십 / 열", "sip / yeol"),
            ClassNumbers("20", "이십 / 스물", "isip / seu-mul"),
            ClassNumbers("30", "삼십 / 서른", "samsip / seo-reun"),
            ClassNumbers("40", "사십 / 마흔", "sasip / ma-heun"),
            ClassNumbers("50", "오십 / 쉰", "osip / swin"),
            ClassNumbers("60", "육십 / 예순", "yuksip / ye-sun"),
            ClassNumbers("70", "칠십 / 일흔", "chilsip / il-heun"),
            ClassNumbers("80", "팔십 / 여든", "palsip / yeo-deun"),
            ClassNumbers("90", "구십 / 아흔", "gusip / a-heun"),
            ClassNumbers("100", "백", "baek"),
            ClassNumbers("1,000", "천", "cheon"),
            ClassNumbers("10,000", "만", "man"),
            ClassNumbers("100,000", "십만", "sip-man"),
            ClassNumbers("1,000,000", "백만 ", "baek-man"),
            ClassNumbers("10,000,000", "천만 ", "cheon-man"),
            ClassNumbers("100,000,000", "일억", "ireok"),
            ClassNumbers("1,000,000,000", "십억", "sibeok"),
            ClassNumbers("10,000,000,000", "백억", "baeg-eok"),

        )

            recyclerView.adapter = NumbersAdapter(koreanWords, this)
            return view
    }
    override fun onItemClick(item: ClassNumbers, position: Int) {
        val speechRate = 0.5f
        val koreanWord = item.koreanNum


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
        val intent = Intent(requireContext(), NumbersInfoActivity::class.java)
        intent.putExtra("englishN", item.englishNum)
        intent.putExtra("koreanN", item.koreanNum)
        intent.putExtra("romanN", item.RomanNum)

        requireContext().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Shutdown Text-to-Speech engine
        tts.shutdown()
    }

}