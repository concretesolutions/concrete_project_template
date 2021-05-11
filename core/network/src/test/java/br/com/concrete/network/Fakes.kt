package br.com.concrete.network

import br.com.concrete.network.dto.OwnerDto
import br.com.concrete.network.dto.RepositoryDto

internal val fakeRepo = RepositoryDto(
    id = 454,
    name = "android-components",
    description = "Components for Android",
    owner = OwnerDto(username = "xpto", "https://imagem.com"),
    starCount = 100,
    forksCount = 200,
)