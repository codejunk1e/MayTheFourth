package io.github.codejunk1e.maythefourth.webservice

import io.github.codejunk1e.maythefourth.responsemodels.BaseResponse
import io.github.codejunk1e.maythefourth.responsemodels.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * @author Robin
 */
interface StarWarsEndpoints {

    @GET("people/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ) : Response <BaseResponse<PeopleResponse>>

}