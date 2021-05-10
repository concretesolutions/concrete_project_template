package br.com.concrete.ghpulls.ui.repos

import br.com.concrete.ghpulls.ui.repos.vo.RepositoryVo
import br.com.concrete.model.Repository

class ReposMapper {
    fun mapModelToVo(repository: Repository) = RepositoryVo(
        id = repository.id,
        name = repository.name,
        description = repository.description,
        starCount = repository.starCount,
        forkCount = repository.forkCount,
        username = repository.username,
        userImageUrl = repository.userImageUrl,
    )
}