package com.aminovic.obs.data.remote

import com.aminovic.obs.data.remote.dto.AthleteDto
import com.aminovic.obs.data.remote.dto.GameDto
import com.aminovic.obs.data.remote.dto.ResultDto
import com.aminovic.obs.domain.utils.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface ObsApi {

    @GET("athletes")
    suspend fun getAthletes(): Resource<List<AthleteDto>>

    @GET("athletes/{id}")
    suspend fun getAthlete(@Path("id") id: Int): Resource<AthleteDto>

    @GET("athletes/{id}/results")
    suspend fun getAthleteResults(@Path("id") id: Int): Resource<List<ResultDto>>

    @GET("games")
    suspend fun getGames(): Resource<List<GameDto>>


    @GET("games/{id}/athletes")
    suspend fun getGameAthletes(@Path("id") id: Int): Resource<GameDto>

    companion object {
        const val baseUrl = "https://ocs-test-backend.onrender.com/"
    }
}