package com.example.project_data_remote.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project_data_remote.KontakApplication
import com.example.project_data_remote.ui.home.viewmodel.HomeViewModel
import com.example.project_data_remote.ui.kontak.viewmodel.InsertViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(apkikasiMars().container.kontakRepository)
        }
        initializer {
            InsertViewModel(apkikasiMars().container.kontakRepository)
        }
    }
}

fun CreationExtras.apkikasiMars(): KontakApplication =
    (this[ ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakApplication)