package br.com.concrete.network.wapper

import br.com.concrete.model.Repository

@JvmInline
value class RepositoryWrapper(
    val repositories: List<Repository>,
)