package com.example.verv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verv.data.MoodRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarFragment : Fragment() {

    private lateinit var adapter: MoodAdapter
    private var selectedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val rvMoods = view.findViewById<RecyclerView>(R.id.rv_calendar_moods)
        val tvSelected = view.findViewById<TextView>(R.id.tv_selected_date)
        val btnAddMood = view.findViewById<Button>(R.id.btn_add_mood_from_calendar)

        adapter = MoodAdapter(listOf()) { _, _ ->
        }

        rvMoods.layoutManager = LinearLayoutManager(context)
        rvMoods.adapter = adapter

        val todayCalendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        selectedDate = dateFormat.format(todayCalendar.time)
        tvSelected.text = "Moods on: $selectedDate"
        val initialFilteredList = MoodRepository.moodList.filter { it.date == selectedDate }
        adapter.updateData(initialFilteredList)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            selectedDate = dateFormat.format(calendar.time)

            tvSelected.text = "Moods on: $selectedDate"

            val filteredList = MoodRepository.moodList.filter { it.date == selectedDate }
            adapter.updateData(filteredList)
        }

        btnAddMood.setOnClickListener {
            val intent = Intent(activity, AddMoodActivity::class.java)
            intent.putExtra("SELECTED_DATE", selectedDate)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        if (selectedDate.isNotEmpty()) {
            val filteredList = MoodRepository.moodList.filter { it.date == selectedDate }
            adapter.updateData(filteredList)
        }
    }
}