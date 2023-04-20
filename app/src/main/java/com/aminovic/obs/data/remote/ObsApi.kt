package com.aminovic.obs.data.remote

import com.aminovic.obs.data.remote.dto.AthleteDto
import com.aminovic.obs.data.remote.dto.GameDto
import com.aminovic.obs.data.remote.dto.ResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ObsApi {

    @GET("athletes")
    suspend fun getAthletes(): List<AthleteDto>

    @GET("athletes/{id}")
    suspend fun getAthlete(@Path("id") id: Int): AthleteDto

    @GET("athletes/{id}/results")
    suspend fun getAthleteResults(@Path("id") id: Int): ResultDto

    @GET("games")
    suspend fun getGames(): List<GameDto>


    @GET("games/{id}/athletes")
    suspend fun getGameAthletes(@Path("id") id: Int): GameDto

    companion object {
        const val baseUrl = "https://ocs-test-backend.onrender.com/"
    }
}