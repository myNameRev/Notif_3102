package com.example.verv

data class User(
    val name: String,
    val email: String,
    val location: String = "Yogyakarta",
    val password: String,
    val phone: String = "0812345678"
)
object UserRepository {
    var currentUser: User = User(
        name = "Nama Pengguna",
        email = "namapengguna@gmail.com",
        location = "Yogyakarta",
        password = "password123",
        phone = "0812345678"
    )
}