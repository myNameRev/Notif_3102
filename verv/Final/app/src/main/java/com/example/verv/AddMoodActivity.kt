package com.example.verv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddMoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mood)

        findViewById<TextView>(R.id.tv_header_text).text = "Add Mood"
        val etTitle = findViewById<EditText>(R.id.et_note_title)
        val btnSave = findViewById<Button>(R.id.btn_save_note)

        btnSave.setOnClickListener {
            val titleInput = etTitle.text.toString()
            if (titleInput.isNotEmpty()) {

                // GUNAKAN FUNGSI DARI REPOSITORY
                val selectedEmoji = MoodRepository.getEmojiFromTitle(titleInput)
                val currentDate = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(Date())

                val newMood = MoodNote(
                    title = titleInput,
                    emoji = selectedEmoji,
                    date = currentDate,
                    isFavorite = false
                )
                MoodRepository.moodList.add(0, newMood)

                Toast.makeText(this, "Mood Saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please write something...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}