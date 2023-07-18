package com.example.miniapp.vocabs

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.databinding.FragmentVocabsBinding
import java.util.Locale

interface ItemClickListener {
    fun onItemClick(item: ClassVocabs, position: Int)
}

class FragmentVocabs : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentVocabsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVocabsBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.VocabsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.KOREAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Language not supported by TTS engine

                }
            } else {
                // TTS engine initialization failed

            }
        }

        val koreanWords = listOf(
            ClassVocabs("Telephone", "전화기", "jeon-hwa-gi", "대", "전화를 걸다/하다", "make a call"),
            ClassVocabs("Cellular Phone", "휴대폰", "hu-dae-pon", "대", "휴대폰을 사용하다", "Using Cellphone"),
            ClassVocabs("Electric Fan", "선풍기", "seon-pung-gi", "대", "선풍기를 틀다/켜다", "Turn on the fan"),
            ClassVocabs("Washing Machine", "세탁기", "se-tak-gi", "대" , "세탁기를 돌리다", "run the washing machine"),
            ClassVocabs("Iron", "다리미", "da-ri-mi", "대" , "다리미질을 하다", "Ironing"),
            ClassVocabs("Television", "텔레비전", "tel-le-bi-jeon", "대" , "텔레비전을 보다", "Watching television"),
            ClassVocabs("Radio", "라디오", "ra-di-o", "대" , "라디오를 듣다", "Listen to the radio"),
            ClassVocabs("Refrigerator", "냉장고", "naeng-jang-go", "대" , "냉장고를 사다", "Buy a refrigerator"),
            ClassVocabs("Aircondition", "에어콘", "e-eo-kon", "대" , "에어콘을 고지다", "fix the air conditioner"),
            ClassVocabs("Vacuum Cleaner", "진공 청소기", "jin-gong cheong-so-gi", "대" , "진공청소기를 돌리다", "run the vacuum cleaner"),
            ClassVocabs("Computer", "컴퓨터", "keom-pyu-teo", "대" , "컴퓨터를 설지하다", "set up a computer"),
            ClassVocabs("Laptop", "노트북 컴퓨터", "no-teu-buk keom-pyu-teo", "대" , "노트북 컴퓨터를 빌리다", "borrowing laptop"),
            ClassVocabs("Hair Dryer", "드라이기", "deu-la-i-gi", "대" , "드라이기로 머리를 말리다", "dry one's hair with a hair dryer"),
            ClassVocabs("Camera", "카메라", "ka-me-la", "대" , "카메라로 사진을 찍다", "take a picture with a camera"),
            ClassVocabs("Mirror", "거울", "geo-ul", "개" , "거울을 보다", "look at the mirror"),
            ClassVocabs("Tissue Paper", "휴지", "hyu-ji", "개" , "휴지가 떨어지다", "toilet paper runs out"),
            ClassVocabs("Soap", "비누", "bi-nu", "개" , "비누로 얼굴을 닦다", "wash one's face with soap"),
            ClassVocabs("Tooth Brush", "칫솔", "chid-ssol", "개" , "칫솔로 이를 닦다", "brush one's teeth with a toothbrush"),
            ClassVocabs("Tooth Paste", "치약", "chi-yak", "개" , "치약을 묻히다", "put toothpaste on"),
            ClassVocabs("Desk", "책상", "chaek-sang", "개" , "책상 아래에 있다", "under the desk"),
            ClassVocabs("Table", "테이블 / 식탁", "te-i-beul / sik-tak", "개" , "테이블 위에 놓다", "put on the table"),
            ClassVocabs("Chair", "의자", "ui-ja", "개" , "의자에 앉다", "sit in a chair"),
            ClassVocabs("Broom", "빗자루 / 비", "bid-ja-ru / bi", "개" , "빗자루로 쓸다", "sweep with a broom"),
            ClassVocabs("Dustpan", "쓰레받기", "sseu-re-bad-gi", "개" , "쓰레받기로 쓸다", "sweep with dustpan"),
            ClassVocabs("Apple", "사과", "sa-gwa", "개" , "사과를 깎다", "peel an apple"),
            ClassVocabs("Watermelon", "수박", "su-bak", "통" , "수박을 몇 조각으로 쪼 개다", "cut the watermelon into several pieces"),
            ClassVocabs("Orange", "오렌지", "o-ren-ji-", "개" , "오렌지를 먹다", "eat an orange"),
            ClassVocabs("Grapes", "포도", "po-do", "송이" , "포도를 따다", "pick grapes"),
            ClassVocabs("Strawberry", "딸기", "ddal-gi", "개" , "딸기를 씻다", "wash strawberries"),
            ClassVocabs("Banana", "바나나", "ba-na-na", "송이" , "바나나를 까다", "peel a banana"),
            ClassVocabs("Cherry", "체리", "che-ri", "개" , "체리로 장식하다", "decorate with cherries"),
            ClassVocabs("Melon", "참외", "cham-oe", "개" , "참외를 따다", "pick melons"),
            ClassVocabs("Pear", "배", "bae", "개" , "배 껍질을 벗기다", "peel a pear"),
        )
        recyclerView.adapter = VocabsAdapter(koreanWords, this)
        return view
    }
    override fun onItemClick(item: ClassVocabs, position: Int) {
        val koreanWord = item.koreanWord
        val koreanSentence = item.sentenceKorean

        when {
            !tts.isSpeaking -> {

                tts.speak(koreanWord, TextToSpeech.QUEUE_ADD, null, "koreanWord")
                tts.speak(koreanSentence, TextToSpeech.QUEUE_ADD, null, "koreanSentence")
                tts.setSpeechRate(0.3f)
            }
            else -> {
                tts.stop()

                tts.speak(koreanWord, TextToSpeech.QUEUE_ADD, null, "koreanWord")
                tts.speak(koreanSentence, TextToSpeech.QUEUE_ADD, null, "koreanSentence")
                tts.setSpeechRate(0.3f)
            }
        }

        val intent = Intent(requireContext(), VocabsInfoActivity::class.java)
        intent.putExtra("english", item.englishWord)
        intent.putExtra("korean", item.koreanWord)
        intent.putExtra("roman", item.romanization)
        intent.putExtra("unit", item.unit)
        intent.putExtra("koreanSentence", item.sentenceKorean)
        intent.putExtra("englishSentence", item.sentenceEnglish)
        requireContext().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Shutdown Text-to-Speech engine
        tts.shutdown()
    }
}