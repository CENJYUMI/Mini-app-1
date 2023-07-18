package com.example.miniapp.vocabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.miniapp.databinding.ActivityVocabsInfoBinding
import java.util.Locale

class VocabsInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVocabsInfoBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocabsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoEnglish.text = intent.getStringExtra("english")
        binding.infoKorean.text = intent.getStringExtra("korean")
        binding.infoRoman.text = intent.getStringExtra("roman")
        binding.infoUnit.text = intent.getStringExtra("unit")
        binding.infoKoreanUsage.text = intent.getStringExtra("koreanSentence")
        binding.infoEnglishUsage.text = intent.getStringExtra("englishSentence")

        // Initialize Text-to-Speech engine

        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // TTS engine successfully initialized
                val result = tts.setLanguage(Locale.KOREAN)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Language not supported by TTS engine
                    Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show()
                    tts.setSpeechRate(0.5f)
                }
            } else {
                // TTS engine initialization failed
                Toast.makeText(this, "TTS initialization failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.infoKorean.setOnClickListener {
            val englishWord = binding.infoKorean.text.toString()
            tts.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, null)
            tts.setSpeechRate(0.5f)
        }
        binding.infoKoreanUsage.setOnClickListener {
            val englishWord = binding.infoKoreanUsage.text.toString()
            tts.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, null)
            tts.setSpeechRate(0.5f)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // Shutdown Text-to-Speech engine
        tts.shutdown()
    }
}
