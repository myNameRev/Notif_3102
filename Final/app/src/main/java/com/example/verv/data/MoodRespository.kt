package com.example.verv.data

object MoodRepository {
    val moodList = mutableListOf(
        MoodNote(title = "Terbang ke Bali", emoji = "✈️", date = "16 Dec 2025", isFavorite = true),
        MoodNote(title = "Belanja Bulanan", emoji = "🛍️", date = "16 Dec 2025", isFavorite = false),
        MoodNote(title = "Sangat Senang", emoji = "😊", date = "10 Dec 2025", isFavorite = false)
    )

    fun getFavorites(): List<MoodNote> {
        return moodList.filter { it.isFavorite }
    }
    fun deleteMood(mood: MoodNote) {
        moodList.remove(mood)
    }
    fun updateMood(index: Int, newMood: MoodNote) {
        if (index >= 0 && index < moodList.size) {
            moodList[index] = newMood
        }
    }
    fun getEmojiFromTitle(title: String): String {
        val lower = title.lowercase()
        return when {
            lower.contains("terbang") || lower.contains("travel") || lower.contains("libur") -> "✈️"
            lower.contains("belanja") || lower.contains("beli") || lower.contains("mall") -> "🛍️"
            lower.contains("lari") || lower.contains("run") || lower.contains("gym") -> "🏃"
            lower.contains("makan") || lower.contains("food") || lower.contains("lapar") -> "🍔"
            lower.contains("minum") || lower.contains("kopi") || lower.contains("cafe") -> "☕"
            lower.contains("kerja") || lower.contains("tugas") || lower.contains("kantor") -> "💼"
            lower.contains("tidur") || lower.contains("sleep") || lower.contains("ngantuk") -> "😴"
            lower.contains("sedih") || lower.contains("nangis") || lower.contains("galau") -> "😭"
            lower.contains("marah") || lower.contains("benci") -> "😡"
            lower.contains("cinta") || lower.contains("sayang") || lower.contains("love") -> "❤️"
            else -> "😊"
        }
    }
}