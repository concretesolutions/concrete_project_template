package br.com.concrete.ghpulls.ui.repos

import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import br.com.concrete.ghpulls.ui.repos.vo.RepositoryVo
import br.com.concrete.model.Repository

class ReposMapper {
    fun mapModelToVo(repository: Repository) = RepositoryVo(
        id = repository.id,
        name = buildSpannedString {
            bold {
                append(repository.name)
            }

            italic {
                append(" from")
            }
        },
        description = repository.description,
        metricsInfo = buildSpannedString {
            bold {
                append("${repository.starCount}")
            }

            italic {
                append(" stars")
            }

            bold {
                append("  ${repository.forkCount}")
            }

            italic {
                append(" forks")
            }
        },
        username = repository.username,
        userImageUrl = repository.userImageUrl,
    )
}