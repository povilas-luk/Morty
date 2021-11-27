package com.example.morty.network

import com.example.morty.network.response.GetCharacterByIdResponse
import com.example.morty.network.response.GetCharactersPageResponse
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val rickAndMortyService: RickAndMortyService
    ) {

    suspend fun getCharacterById(characterId: Int) : SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }

}
