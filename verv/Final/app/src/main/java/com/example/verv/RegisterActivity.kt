package com.example.verv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Binding Views
        val btnRegister = findViewById<Button>(R.id.btn_register)
        val tvLogin = findViewById<TextView>(R.id.tv_login)

        val etName = findViewById<EditText>(R.id.et_reg_name)
        val etEmail = findViewById<EditText>(R.id.et_reg_email)
        val etPhone = findViewById<EditText>(R.id.et_reg_phone)     // Baru
        val etAddress = findViewById<EditText>(R.id.et_reg_address) // Baru
        val etPassword = findViewById<EditText>(R.id.et_reg_password)

        btnRegister.setOnClickListener {
            // Ambil text dari input
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val address = etAddress.text.toString()
            val password = etPassword.text.toString()

            // Validasi sederhana: Pastikan tidak ada yang kosong
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {

                // --- SIMPAN DATA KE REPOSITORY ---
                val newUser = User(
                    name = name,
                    email = email,
                    password = password,
                    phone = phone,       // Simpan No HP inputan user
                    location = address   // Simpan Alamat inputan user
                )

                // Update User yang sedang aktif
                UserRepository.currentUser = newUser
                // ---------------------------------

                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

                // Pindah ke Login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            finish() // Kembali ke Login
        }
    }
}