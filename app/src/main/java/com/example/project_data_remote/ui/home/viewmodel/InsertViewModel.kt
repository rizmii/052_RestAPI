package com.example.project_data_remote.ui.home.viewmodel

import com.example.project_data_remote.model.Kontak
import retrofit2.http.GET
import retrofit2.http.Headers

data class InsertUiEvent(
    val id: Int = 0,
    val nama: String = "",
    val email: String = "",
    val no_hp: String = "",
)
fun InsertUiEvent.toKontak() : Kontak = Kontak(
    id = id,
    nama = nama,
    alamat = email,
    nohp = no_hp,
)
data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

fun Kontak.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    id = id,
    nama = nama,

)