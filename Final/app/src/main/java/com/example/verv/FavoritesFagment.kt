package com.example.verv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verv.data.MoodRepository

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val rvMoodList = view.findViewById<RecyclerView>(R.id.rv_mood_list)

        val favoriteList = MoodRepository.getFavorites()
        val adapter = MoodAdapter(favoriteList) { _, _ ->
        }

        rvMoodList.layoutManager = LinearLayoutManager(context)
        rvMoodList.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        val rvMoodList = view?.findViewById<RecyclerView>(R.id.rv_mood_list)
        val favoriteList = MoodRepository.getFavorites()
        (rvMoodList?.adapter as? MoodAdapter)?.updateData(favoriteList)
    }
}