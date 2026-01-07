package com.example.verv

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val rvMoodList = view.findViewById<RecyclerView>(R.id.rv_mood_list)

        // Setup Adapter
        // Bagian { mood, position -> ... } adalah kode yang jalan saat item ditekan lama
        adapter = MoodAdapter(MoodRepository.moodList) { mood, position ->
            showOptionsDialog(mood, position)
        }

        rvMoodList.layoutManager = LinearLayoutManager(context)
        rvMoodList.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(MoodRepository.moodList)
    }

    // --- 1. Dialog Pilihan ---
    private fun showOptionsDialog(mood: MoodNote, position: Int) {
        val options = arrayOf("Edit", "Delete")
        AlertDialog.Builder(requireContext())
            .setTitle("Choose Action")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showEditDialog(mood, position)
                    1 -> showDeleteDialog(mood, position)
                }
            }
            .show()
    }

    // --- 2. Dialog Hapus ---
    private fun showDeleteDialog(mood: MoodNote, position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Mood")
            .setMessage("Are you sure you want to delete '${mood.title}'?")
            .setPositiveButton("Delete") { _, _ ->
                MoodRepository.deleteMood(mood)

                // Animasi hapus di list
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, MoodRepository.moodList.size)
                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // --- 3. Dialog Edit ---
    private fun showEditDialog(mood: MoodNote, position: Int) {
        val editText = EditText(requireContext())
        editText.setText(mood.title)

        // Sedikit styling agar input tidak mepet
        val container = android.widget.FrameLayout(requireContext())
        val params = android.widget.FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.leftMargin = 50; params.rightMargin = 50
        editText.layoutParams = params
        container.addView(editText)

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Mood")
            .setView(container)
            .setPositiveButton("Save") { _, _ ->
                val newTitle = editText.text.toString()
                if (newTitle.isNotEmpty()) {

                    // Update Emoji otomatis berdasarkan judul baru
                    val newEmoji = MoodRepository.getEmojiFromTitle(newTitle)

                    // Copy data lama, ganti judul & emoji saja
                    val updatedMood = mood.copy(title = newTitle, emoji = newEmoji)

                    MoodRepository.updateMood(position, updatedMood)
                    adapter.notifyItemChanged(position)
                    Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}