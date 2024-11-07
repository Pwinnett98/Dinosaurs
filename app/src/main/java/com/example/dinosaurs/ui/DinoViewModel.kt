package com.example.dinosaurs.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaurs.network.DinoApi
import com.example.dinosaurs.network.DinoPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinoUiState {
    data class Success(val photos: List<DinoPhoto>) : DinoUiState
    object Error : DinoUiState
    object Loading : DinoUiState
}

class DinoViewModel : ViewModel() {

    var dinoUiState: DinoUiState by mutableStateOf(DinoUiState.Loading)
        private set

    init {
        getDinoPhotos()
    }

    /**
     [DinoPhoto] [List] [MutableList].
     */
    fun getDinoPhotos() {
        viewModelScope.launch {
            dinoUiState = try {
                DinoUiState.Success(DinoApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinoUiState.Error
            }
        }
    }
}