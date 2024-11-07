package com.example.dinosaurs.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DinoPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val img_src: String
)