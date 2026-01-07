package com.example.verv

data class User(
    val name: String,
    val email: String,
    val location: String = "Yogyakarta", // Default value sesuai gambar
    val password: String,
    val phone: String = "0812345678"     // Default value sesuai gambar
)

// Tempat penyimpanan data user sementara (Singleton)
object UserRepository {
    // Data dummy awal (agar tidak kosong saat pertama run)
    var currentUser: User = User(
        name = "Nama Pengguna",
        email = "namapengguna@gmail.com",
        location = "Yogyakarta",
        password = "password123",
        phone = "0812345678"
    )
}