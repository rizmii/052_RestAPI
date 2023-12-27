package com.example.project_data_remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kontak(
    val id: Int,
    val nama:String,
    @SerialName(value = "email")
    val alamat: String,
    @SerialName(value = "no_hp")
    val nohp: String,
)
