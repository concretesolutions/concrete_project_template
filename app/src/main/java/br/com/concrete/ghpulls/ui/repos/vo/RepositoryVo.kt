package br.com.concrete.ghpulls.ui.repos.vo

import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import br.com.concrete.model.Repository

sealed class RepoBaseVo {
    data class Header(@StringRes val titleRes: Int) : RepoBaseVo()
    data class RepositoryVo(
        val id: Int,
        val name: CharSequence,
        val description: String,
        val metricsInfo: CharSequence,
        val username: String,
        val userImageUrl: String,
        val isFavorite: Boolean,
        val repositoryModel: Repository
    ) : RepoBaseVo()

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RepoBaseVo>() {
            override fun areItemsTheSame(oldItem: RepoBaseVo, newItem: RepoBaseVo): Boolean {
                return if (oldItem is RepositoryVo && newItem is RepositoryVo) {
                    oldItem.id == newItem.id
                } else {
                    oldItem == newItem
                }
            }

            override fun areContentsTheSame(oldItem: RepoBaseVo, newItem: RepoBaseVo): Boolean {
                return if (oldItem is RepositoryVo && newItem is RepositoryVo) {
                    oldItem.isFavorite == newItem.isFavorite
                } else {
                    oldItem == newItem
                }
            }
        }
    }
}

