package com.example.verv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // 1. Binding Views Profil
        val etName = view.findViewById<TextInputEditText>(R.id.et_profile_name)
        val etEmail = view.findViewById<TextInputEditText>(R.id.et_profile_email)
        val etAddress = view.findViewById<TextInputEditText>(R.id.et_profile_address)
        val etPhone = view.findViewById<TextInputEditText>(R.id.et_profile_phone)
        val etPassword = view.findViewById<TextInputEditText>(R.id.et_profile_password)

        // 2. Tampilkan Data User
        val currentUser = UserRepository.currentUser
        etName.setText(currentUser.name)
        etEmail.setText(currentUser.email)
        etAddress.setText(currentUser.location)
        etPhone.setText(currentUser.phone)
        etPassword.setText(currentUser.password)

        // 3. LOGIKA DARK MODE
        val switchDarkMode = view.findViewById<MaterialSwitch>(R.id.switch_dark_mode)

        // Siapkan Penyimpanan Data (Agar pilihan user diingat)
        val sharedPreferences = requireActivity().getSharedPreferences("AppSetting", Context.MODE_PRIVATE)

        // Ambil status terakhir (Default False / Mode Terang)
        val isDarkMode = sharedPreferences.getBoolean("NightMode", false)
        switchDarkMode.isChecked = isDarkMode

        // Listener saat Switch diubah
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Aktifkan Mode Gelap
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferences.edit().putBoolean("NightMode", true).apply()
            } else {
                // Aktifkan Mode Terang
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferences.edit().putBoolean("NightMode", false).apply()
            }
        }

        return view
    }
}