package com.example.verv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Inisialisasi View
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val tvRegister = findViewById<TextView>(R.id.tv_register)

        // 2. Aksi Tombol Login
        btnLogin.setOnClickListener {
            val inputEmail = etEmail.text.toString().trim() // .trim() menghapus spasi tidak sengaja
            val inputPassword = etPassword.text.toString().trim()

            // Validasi Input Kosong
            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show()
            } else {
                // --- LOGIKA UTAMA: BANDINGKAN DENGAN DATA REGISTER ---

                // Ambil data user yang tersimpan saat ini
                val registeredUser = UserRepository.currentUser

                // Cek apakah Email DAN Password cocok
                if (inputEmail == registeredUser.email && inputPassword == registeredUser.password) {

                    // JIKA COCOK:
                    Toast.makeText(this, "Welcome back, ${registeredUser.name}!", Toast.LENGTH_SHORT).show()

                    // Pindah ke Halaman Utama
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Tutup halaman login agar tidak bisa back

                } else {
                    // JIKA TIDAK COCOK:
                    Toast.makeText(this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 3. Link ke Halaman Register
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}