package com.example.verv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarFragment : Fragment() {

    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val rvMoods = view.findViewById<RecyclerView>(R.id.rv_calendar_moods)
        val tvSelected = view.findViewById<TextView>(R.id.tv_selected_date)

        // PERBAIKAN DI SINI:
        // Inisialisasi Adapter dengan list kosong dan lambda kosong { _, _ -> }
        adapter = MoodAdapter(listOf()) { _, _ ->
            // Biarkan kosong, fitur edit/hapus dimatikan di halaman kalender
        }

        rvMoods.layoutManager = LinearLayoutManager(context)
        rvMoods.adapter = adapter

        // Listener saat tanggal kalender diklik
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->

            // Format tanggal
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
            val selectedDateStr = dateFormat.format(calendar.time)

            tvSelected.text = "Moods on: $selectedDateStr"

            // Filter data dari Repository berdasarkan tanggal
            val filteredList = MoodRepository.moodList.filter { it.date == selectedDateStr }

            // Update List di bawah kalender
            adapter.updateData(filteredList)
        }

        return view
    }
}