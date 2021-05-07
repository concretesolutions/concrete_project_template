package br.com.concrete.network.dto

import com.squareup.moshi.Json

data class RepositoryWrapperDto(
    @Json(name = "items") val items: List<RepositoryDto>
)

data class RepositoryDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "owner") val owner: OwnerDto,
    @Json(name = "stargazers_count") val starCount: String,
    @Json(name = "forks") val forksCount: String,
)

data class OwnerDto(
    @Json(name = "login") val username: String,
    @Json(name = "avatar_url") val avatarUrl: String,
)
