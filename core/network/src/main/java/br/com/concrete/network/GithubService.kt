package br.com.concrete.network

import br.com.concrete.model.RepositoryWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories?q=language:Kotlin&sort=stars")
    suspend fun getRepos(
        @Query("page") pageNumber: Int,
        @Query ( "q") query: String? = null,
    ): RepositoryWrapper

}