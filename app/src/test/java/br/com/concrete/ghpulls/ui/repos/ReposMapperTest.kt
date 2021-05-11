package br.com.concrete.ghpulls.ui.repos

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.concrete.model.Repository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReposMapperTest {
    private val reposMapper = ReposMapper()

    @Test
    fun mapModelToVo_isCorrect() {
        val fakeRepository = Repository(
            id = 100,
            name = "android-components",
            description = "Android components",
            starCount = 200,
            forkCount = 100,
            username = "android",
            userImageUrl = "http://imagem.com",
        )

        val result = reposMapper.mapModelToVo(fakeRepository)

        assertEquals(result.id, 100)
        assertEquals(result.name.toString(), "android-components from")
        assertEquals(result.description, "Android components")
        assertEquals(result.metricsInfo.toString(), "200 stars  100 forks")
        assertEquals(result.username, "android")
        assertEquals(result.userImageUrl, "http://imagem.com")
    }
}