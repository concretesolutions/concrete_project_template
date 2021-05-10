package br.com.concrete.ghpulls.ui.repos.vo

import androidx.recyclerview.widget.DiffUtil

data class RepositoryVo(
    val id: Int,
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val username: String,
    val userImageUrl: String
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RepositoryVo>() {
            override fun areItemsTheSame(oldItem: RepositoryVo, newItem: RepositoryVo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RepositoryVo, newItem: RepositoryVo): Boolean {
                return oldItem == newItem
            }
        }
    }
}