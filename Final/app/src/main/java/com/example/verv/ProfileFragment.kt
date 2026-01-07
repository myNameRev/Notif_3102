package com.example.verv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val etName = view.findViewById<TextInputEditText>(R.id.et_profile_name)
        val etEmail = view.findViewById<TextInputEditText>(R.id.et_profile_email)
        val etAddress = view.findViewById<TextInputEditText>(R.id.et_profile_address)
        val etPhone = view.findViewById<TextInputEditText>(R.id.et_profile_phone)
        val etPassword = view.findViewById<TextInputEditText>(R.id.et_profile_password)

        val currentUser = UserRepository.currentUser

        etName.setText(currentUser.name)
        etEmail.setText(currentUser.email)
        etAddress.setText(currentUser.location)
        etPhone.setText(currentUser.phone)
        etPassword.setText(currentUser.password)

        return view
    }
}