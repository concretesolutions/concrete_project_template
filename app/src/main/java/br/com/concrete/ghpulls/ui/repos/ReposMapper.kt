package br.com.concrete.ghpulls.ui.repos

import android.content.Context
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import br.com.concrete.database.entity.RepositoryEntity
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.model.Repository

class ReposMapper(
    private val context: Context
) {
    fun mapVoToModel(repoVo: RepoBaseVo.RepositoryVo) = RepositoryEntity(
        id = repoVo.id,
        name = repoVo.name.toString(),
        description = repoVo.description,
        username = repoVo.username,
        userImageUrl = repoVo.userImageUrl,
        forkCount = 0,
        starCount = 0
    )

    fun mapRepositoryEntityToRepository(repositoryEntity: RepositoryEntity) = Repository(
        id = repositoryEntity.id,
        name = repositoryEntity.name.toString(),
        description =  repositoryEntity.description,
        username = repositoryEntity.username,
        userImageUrl = repositoryEntity.userImageUrl,
        forkCount = 0,
        starCount = 0
    )

    fun mapRepositoryToVo(repository: Repository) = RepoBaseVo.RepositoryVo(
        id = repository.id,
        name = buildSpannedString {
            bold {
                append(repository.name)
            }

            italic {
                append(context.getString(R.string.reponame_sufix))
            }
        },
        description = repository.description,
        metricsInfo = buildSpannedString {
            bold {
                append("${repository.starCount}")
            }

            italic {
                append(
                    context.resources.getQuantityString(
                        R.plurals.repostars_sufix,
                        repository.starCount
                    )
                )
            }

            bold {
                append("  ${repository.forkCount}")
            }

            italic {
                append(
                    context.resources.getQuantityString(
                        R.plurals.repoforks_sufix,
                        repository.forkCount
                    )
                )
            }
        },
        username = repository.username,
        userImageUrl = repository.userImageUrl,
    )

    fun mapModelToVo(repositoryEntity: RepositoryEntity) = RepoBaseVo.RepositoryVo(
        id = repositoryEntity.id,
        name = buildSpannedString {
            bold {
                append(repositoryEntity.name)
            }

            italic {
                append(context.getString(R.string.reponame_sufix))
            }
        },
        description = repositoryEntity.description,
        metricsInfo = buildSpannedString {
            bold {
                append("${repositoryEntity.starCount}")
            }

            italic {
                append(
                    context.resources.getQuantityString(
                        R.plurals.repostars_sufix,
                        repositoryEntity.starCount
                    )
                )
            }

            bold {
                append("  ${repositoryEntity.forkCount}")
            }

            italic {
                append(
                    context.resources.getQuantityString(
                        R.plurals.repoforks_sufix,
                        repositoryEntity.forkCount
                    )
                )
            }
        },
        username = repositoryEntity.username,
        userImageUrl = repositoryEntity.userImageUrl,
    )
}