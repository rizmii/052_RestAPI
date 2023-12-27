package com.example.project_data_remote

import android.app.Application
import com.example.project_data_remote.repository.AppContainer
import com.example.project_data_remote.repository.KontakContainer

class KontakApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}