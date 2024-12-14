package com.febriandi.agrojaya.screens.pengingat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febriandi.agrojaya.data.Repository.PengingatRepository
import com.febriandi.agrojaya.model.Pengingat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PengingatViewModel @Inject constructor(
    private val pengingatRepository: PengingatRepository
) : ViewModel() {

    private val _daftarPengingat = MutableStateFlow<List<Pengingat>>(emptyList())
    val daftarPengingat: StateFlow<List<Pengingat>> = _daftarPengingat.asStateFlow()
    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()
    private val _selectedPengingat = MutableStateFlow<Pengingat?>(null)
    val selectedPengingat: StateFlow<Pengingat?> = _selectedPengingat.asStateFlow()

    init {
        muatDaftarPengingat(LocalDate.now())
    }

    fun muatDaftarPengingat(tanggal: LocalDate) {
        viewModelScope.launch {
            _selectedDate.value = tanggal
            pengingatRepository.getDaftarPengingatByTanggal(tanggal)
                .collect { pengingat ->
                    val daftarPengingatTerurut = pengingat.sortedBy { it.jam }
                    _daftarPengingat.value = daftarPengingatTerurut
                }
        }
    }

    fun tambahPengingat(pengingat: Pengingat) {
        viewModelScope.launch {
            pengingatRepository.tambahPengingat(pengingat)
        }
    }

    fun getPengingatById(id: Int) {
        viewModelScope.launch {
            val pengingat = pengingatRepository.getPengingatById(id).first()
            _selectedPengingat.value = pengingat
        }
    }

    fun perbaruidPengingat(pengingat: Pengingat) {
        viewModelScope.launch {
            pengingatRepository.perbaruidPengingat(pengingat)
        }
    }

    fun hapusPengingat(pengingat: Pengingat) {
        viewModelScope.launch {
            pengingatRepository.hapusPengingat(pengingat)
        }
    }
}