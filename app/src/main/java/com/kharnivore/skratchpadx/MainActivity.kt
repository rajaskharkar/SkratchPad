package com.kharnivore.skratchpadx

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    private val PREF_NAME = "SkratchPadPrefs"
    private val SKRATCH_PAD_TEXT_KEY = "skratch_pad_text_key"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val typeface = ResourcesCompat.getFont(this, R.font.caveatsb)
        var titleTextView = findViewById<TextView>(R.id.text_title)
        titleTextView.typeface = typeface
        val editText = findViewById<EditText>(R.id.skratchpad_text)
        var text = sharedPreferences.getString(
            SKRATCH_PAD_TEXT_KEY, "Hi!\n\nWelcome to SkratchPad!\n\n"
        )
        editText.setText(text)
        editText.setSelection(editText.text.length)
        editText.requestFocus()
    }

    override fun onPause() {
        super.onPause()
        // Save text to SharedPreferences
        val editor = sharedPreferences.edit()
        val editText = findViewById<EditText>(R.id.skratchpad_text)
        editor.putString(SKRATCH_PAD_TEXT_KEY, editText.text.toString())
        editor.apply()
    }
}
