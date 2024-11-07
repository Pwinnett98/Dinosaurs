package com.example.dinosaurs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.dinosaurs.network.DinoApiService
import com.example.dinosaurs.network.DinoApi

sealed interface DinoUiState {
    data class Success(val photos: String) : DinoUiState
    object Error : DinoUiState
    object Loading : DinoUiState
}


class DinoViewModel: ViewModel() {
    var dinoUiState: DinoUiState by mutableStateOf(DinoUiState.Loading)
        private set
    init {
        getDinoPhotos()
    }

    /**
     * [DinoPhoto] [List] [MutableList].
     */
    fun getDinoPhotos() {
        viewModelScope.launch {
            try {
                val listResult = DinoApi.retrofitService.getPhotos()
                dinoUiState = DinoUiState.Success("Success: ${listResult.size} Mars photos retrieved")
            } catch (e: IOException) {
                dinoUiState = DinoUiState.Error
            }
        }
        }
    }
