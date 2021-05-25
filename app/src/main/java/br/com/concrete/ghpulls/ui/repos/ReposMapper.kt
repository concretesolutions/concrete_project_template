package br.com.concrete.ghpulls.ui.repos

import android.content.Context
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.model.Repository

class ReposMapper(
    private val context: Context
) {
    fun mapModelToVo(repository: Repository) = RepoBaseVo.RepositoryVo(
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
}
