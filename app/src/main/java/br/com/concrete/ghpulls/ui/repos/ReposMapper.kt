package br.com.concrete.ghpulls.ui.repos

import br.com.concrete.ghpulls.ui.repos.vo.RepositoryVo
import br.com.concrete.model.Repository

class ReposMapper {
    fun mapModelToVo(repository: Repository) = RepositoryVo(
        repository.name
    )
}