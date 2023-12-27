package com.example.project_data_remote.repository

import com.example.project_data_remote.model.Kontak
import com.example.project_data_remote.network.KontakService
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import java.io.IOException
import java.lang.Exception

interface KontakRepository {
    suspend fun getKontak():List<Kontak>
    suspend fun insertKontak(kontak: Kontak)
    suspend fun updateKontak(@Path("id")id:Int, @Body kontak: Kontak )
    suspend fun deleteKontak(@Path("id")id: Int ): Response<Void>
    suspend fun getKontakById(@Path("id") id: Int): Kontak
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
):KontakRepository{
    override suspend fun getKontak():List<Kontak> = kontakApiService.getKontak()

    override suspend fun deleteKontak(id: Int): Response<Void> {
        try {
            val response = kontakApiService.deleteKontak(id)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kontak. HTTP status code:${response.code()} ")
            }
            else{
                response.message()
            }
        }
        catch (e: Exception){
            throw e
        }
    }

    override suspend fun getKontakById(id: Int): Kontak {
        return kontakApiService.getKontakById(id)
    }

    override suspend fun insertKontak(kontak: Kontak) {
        kontakApiService.insertKontak(kontak)
    }

    override suspend fun updateKontak(id: Int, kontak: Kontak) {
        kontakApiService.updateKontak(id, kontak)
    }
}