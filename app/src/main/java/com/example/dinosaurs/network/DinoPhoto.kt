package com.example.dinosaurs.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DinoPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)

