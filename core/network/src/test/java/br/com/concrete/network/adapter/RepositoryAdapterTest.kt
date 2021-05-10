package br.com.concrete.network.adapter

import br.com.concrete.network.dto.RepositoryWrapperDto
import br.com.concrete.network.fakeRepo
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryAdapterTest {
    private val repoAdapter = RepositoryAdapter()

    @Test
    fun dtoRepositoryWrapperItems_shouldMapToModelWrapperItems() {
        val wrapperDto = RepositoryWrapperDto(items = listOf(fakeRepo))

        val result = repoAdapter.repositoryWrapperDtoFromWrapperModel(wrapperDto)

        assertEquals(result.repositories.size, 1)
        with(result.repositories[0]) {
            assertEquals(id, 454)
            assertEquals(name, "android-components")
            assertEquals(description, "Components for Android")
            assertEquals(username, "xpto")
            assertEquals(userImageUrl, "https://imagem.com")
            assertEquals(starCount, 100)
            assertEquals(forkCount, 200)
        }
    }

    @Test
    fun dtoRepositoryWrapperItemsNoDescription_shouldMapToModelWrapperItems() {
        val repoNoDescription = fakeRepo.copy(
            description = null
        )

        val result = repoAdapter.repositoryDtoToModel(repoNoDescription)

        with(result) {
            assertEquals(id, 454)
            assertEquals(name, "android-components")
            assertEquals(description, "No description provided")
            assertEquals(username, "xpto")
            assertEquals(userImageUrl, "https://imagem.com")
            assertEquals(starCount, 100)
            assertEquals(forkCount, 200)
        }
    }
}