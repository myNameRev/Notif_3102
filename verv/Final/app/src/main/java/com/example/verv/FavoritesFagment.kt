package com.example.verv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Reuse layout fragment_home karena isinya sama (RecyclerView)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val rvMoodList = view.findViewById<RecyclerView>(R.id.rv_mood_list)

        // Sembunyikan FAB (Tombol Tambah) jika ada, karena ini halaman Favorit
        view.findViewById<View>(R.id.fab_add)?.visibility = View.GONE

        // Ambil data Favorit dari Repository
        val favoriteList = MoodRepository.getFavorites()

        // PERBAIKAN DI SINI:
        // Kita tambahkan lambda kosong { _, _ -> } di akhir.
        // Artinya: Jika item di Favorit ditekan lama, tidak akan terjadi apa-apa (tidak bisa edit/hapus di sini).
        val adapter = MoodAdapter(favoriteList) { _, _ ->
            // Biarkan kosong jika tidak ingin fitur edit/hapus di halaman Favorit
        }

        rvMoodList.layoutManager = LinearLayoutManager(context)
        rvMoodList.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        // Refresh data saat kembali ke tab ini (jika ada perubahan status love)
        val rvMoodList = view?.findViewById<RecyclerView>(R.id.rv_mood_list)
        val favoriteList = MoodRepository.getFavorites()

        // Update data di adapter
        (rvMoodList?.adapter as? MoodAdapter)?.updateData(favoriteList)
    }
}