package br.com.concrete.ghpulls.ui.repos

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.concrete.model.Repository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class ReposMapperTest : AutoCloseKoinTest() {
    private val reposMapper: ReposMapper by inject()

    @Test
    fun mapModelToVo_isCorrect() {
        val fakeRepository = Repository(
            id = 100,
            name = "android-components",
            description = "Android components",
            starCount = 1,
            forkCount = 2,
            username = "android",
            userImageUrl = "http://imagem.com",
        )

        val result = reposMapper.mapModelToVo(fakeRepository)

        assertEquals(100, result.id)
        assertEquals("android-components from", result.name.toString())
        assertEquals("Android components", result.description)
        assertEquals("1 star  2 forks", result.metricsInfo.toString())
        assertEquals("android", result.username)
        assertEquals("http://imagem.com", result.userImageUrl)
    }
}