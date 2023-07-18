package com.example.miniapp.alphabets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.miniapp.R
import com.example.miniapp.databinding.ActivityAlphabetsInfoBinding
import com.example.miniapp.databinding.ActivityNumbersInfoBinding
import java.util.Locale

class AlphabetsInfoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAlphabetsInfoBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoEnglishA.text = intent.getStringExtra("englishA")
        binding.infoKoreanA.text = intent.getStringExtra("koreanA")
        binding.infoRomanA.text = intent.getStringExtra("romanA")

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

        binding.infoKoreanA .setOnClickListener{
            val koreanWord = binding.infoKoreanA.text.toString()
            tts.speak(koreanWord, TextToSpeech.QUEUE_FLUSH, null, null)
            tts.setSpeechRate(0.5f)
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        // Shutdown Text-to-Speech engine
        tts.shutdown()

    }
}