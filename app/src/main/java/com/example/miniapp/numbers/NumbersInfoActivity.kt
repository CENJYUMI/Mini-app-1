package com.example.miniapp.numbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.miniapp.R
import com.example.miniapp.databinding.ActivityNumbersInfoBinding
import com.example.miniapp.databinding.ActivityPhrasesInfoBinding
import java.util.Locale

class NumbersInfoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNumbersInfoBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumbersInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoEnglishN.text = intent.getStringExtra("englishN")
        binding.infoKoreanN.text = intent.getStringExtra("koreanN")
        binding.infoRomanN.text = intent.getStringExtra("romanN")

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

        binding.infoKoreanN.setOnClickListener{
            val koreanWord = binding.infoKoreanN.text.toString()
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