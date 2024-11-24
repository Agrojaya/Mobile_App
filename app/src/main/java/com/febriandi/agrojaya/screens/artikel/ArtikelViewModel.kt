package com.febriandi.agrojaya.screens.artikel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febriandi.agrojaya.data.Repository.ArtikelRepository
import com.febriandi.agrojaya.model.ArtikelResponse
import com.febriandi.agrojaya.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtikelViewModel @Inject constructor(
    private val repository: ArtikelRepository
) : ViewModel() {

    private val _artikelState = MutableStateFlow<Resource<List<ArtikelResponse>>>(Resource.Loading)
    val artikelState: StateFlow<Resource<List<ArtikelResponse>>> = _artikelState

    private var originalArtikels: List<ArtikelResponse> = emptyList()

    init {
        loadArtikels()
    }

    fun loadArtikels() {
        viewModelScope.launch {
            _artikelState.value = Resource.Loading
            try {
                val result = repository.getArtikels()
                if (result is Resource.Success) {
                    originalArtikels = result.data
                }
                _artikelState.value = result
            } catch (e: Exception) {
                _artikelState.value = Resource.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }

    fun searchArtikels(query: String) {
        viewModelScope.launch {
            try {
                if (query.isEmpty()) {
                    _artikelState.value = Resource.Success(originalArtikels)
                } else {
                    val filteredList = originalArtikels.filter { artikel ->
                        artikel.judul.contains(query, ignoreCase = true) ||
                                artikel.isi.contains(query, ignoreCase = true)
                    }
                    _artikelState.value = Resource.Success(filteredList)
                }
            } catch (e: Exception) {
                _artikelState.value = Resource.Error("Terjadi kesalahan saat mencari artikel")
            }
        }
    }
}