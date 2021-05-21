package br.com.concrete.network.adapter

import br.com.concrete.model.Repository
import br.com.concrete.model.RepositoryWrapper
import br.com.concrete.network.dto.RepositoryDto
import br.com.concrete.network.dto.RepositoryWrapperDto
import com.squareup.moshi.FromJson

internal class RepositoryAdapter {
    @FromJson
    fun repositoryWrapperDtoFromWrapperModel(wrapperDto: RepositoryWrapperDto) = RepositoryWrapper(
        wrapperDto.items.map {
            repositoryDtoToModel(it)
        }
    )

    internal fun repositoryDtoToModel(repositoryDto: RepositoryDto) = Repository(
        id = repositoryDto.id,
        name = repositoryDto.name,
        description = repositoryDto.description ?: "No description provided",
        starCount = repositoryDto.starCount,
        forkCount = repositoryDto.forksCount,
        username = repositoryDto.owner.username,
        userImageUrl = repositoryDto.owner.avatarUrl,
    )
}
