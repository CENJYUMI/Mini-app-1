package com.example.miniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.miniapp.alphabets.FragmentAlphabets
import com.example.miniapp.databinding.ActivityHomePageBinding
import com.example.miniapp.numbers.FragmentNumber
import com.example.miniapp.phrases.FragmentPhrases
import com.example.miniapp.vocabs.FragmentVocabs
import org.intellij.lang.annotations.Language

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Fragments
        val fHome = FragmentHomePage()
        val fVocabs = FragmentVocabs()
        val fPhrases = FragmentPhrases()
        val fNum = FragmentNumber()
        val fAlpha = FragmentAlphabets()

        //default fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fHome)
            commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuVocabs -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, fVocabs)
                        commit()
                    }
                }

                R.id.menuPhrases -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, fPhrases)
                        commit()
                    }
                }

                R.id.menuNum -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, fNum)
                        commit()
                    }
                }

                R.id.menuAlphabets -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, fAlpha)
                        commit()
                    }
                }
            }
            true
        }
        binding.materialToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.appBarAbout -> {
                    showAlertDialog("")
                }
            }
            true

        }


    }

    private fun showAlertDialog(message: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_box, null)
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogAbout)
        val dialogButton = dialogView.findViewById<TextView>(R.id.dialogBackBtn)

        dialogTitle.text = "About the Developer and the App"
        dialogMessage.text = "Welcome to the Korean Language Learning App! Developed and designed by Mary Claire, this app is the perfect tool for those interested in mastering the Korean language.\n\nMary Claire is a talented mobile developer with a passion for creating engaging and educational applications. With expertise in app development and a strong background in language learning, she has designed a remarkable app specifically tailored for those who want to learn the Korean language.\n\nGet ready to embark on an exciting linguistic adventure with the Korean Language Learning App. Start your journey today and witness your Korean language skills flourish like never before!"


        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogView)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        dialogButton.setOnClickListener {
            Toast.makeText(applicationContext, "Back to Home page", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
    }
}
