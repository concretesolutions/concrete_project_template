package br.com.concrete.network.adapter

import br.com.concrete.model.Repository
import br.com.concrete.network.dto.RepositoryDto
import br.com.concrete.network.dto.RepositoryWrapperDto
import br.com.concrete.network.wapper.RepositoryWrapper
import com.squareup.moshi.FromJson

class RepositoryAdapter {
    private fun repositoryDtoToModel(repositoryDto: RepositoryDto) = Repository(
        id = repositoryDto.id,
        name = repositoryDto.name,
        description = repositoryDto.description,
        starCount = repositoryDto.starCount,
        forkCount = repositoryDto.forksCount,
        username = repositoryDto.owner.username,
        userImageUrl = repositoryDto.owner.avatarUrl,
    )

    @FromJson
    internal fun repositoryWrapperToClass(wrapperDto: RepositoryWrapperDto) = RepositoryWrapper(
        wrapperDto.items.map {
            repositoryDtoToModel(it)
        }
    )
}