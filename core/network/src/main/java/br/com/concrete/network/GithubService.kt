package br.com.concrete.network

import br.com.concrete.network.wapper.RepositoryWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("search/repositories?q=language:Kotlin&sort=stars&page={pageNumber}")
    fun getRepos(
        @Path("pageNumber") pageNumber: Int,
    ): RepositoryWrapper
}