package br.com.concrete.ghpulls.ui.repos.vo

import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil

sealed class RepoBaseVo {
    data class Header(@StringRes val titleRes: Int) : RepoBaseVo()
    data class RepositoryVo(
        val id: Int,
        val name: CharSequence,
        val description: String,
        val metricsInfo: CharSequence,
        val username: String,
        val userImageUrl: String
    ) : RepoBaseVo()

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RepoBaseVo>() {
            override fun areItemsTheSame(oldItem: RepoBaseVo, newItem: RepoBaseVo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RepoBaseVo, newItem: RepoBaseVo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
