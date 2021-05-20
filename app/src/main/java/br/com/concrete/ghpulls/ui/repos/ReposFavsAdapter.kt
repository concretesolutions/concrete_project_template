package br.com.concrete.ghpulls.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.databinding.ItemHeaderRepoBinding
import br.com.concrete.ghpulls.databinding.ItemRepoBinding
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.ghpulls.util.extension.loadUrl

class ReposFavsAdapter (private val reposClickCallback: ReposClickCallback?) :
    PagingDataAdapter<RepoBaseVo, RecyclerView.ViewHolder>(RepoBaseVo.diffUtil) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            if (holder is RepositoryItemViewHolder) {
                holder.bind(item as RepoBaseVo.RepositoryVo)
            }

            if (holder is HeaderItemViewHolder) {
                holder.bind(item as RepoBaseVo.Header)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RepoBaseVo.RepositoryVo -> R.layout.item_repo
            else -> R.layout.item_header_repo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val reposBinding = ItemRepoBinding.inflate(layoutInflater, parent, false)
        reposBinding.callback = reposClickCallback

        return when (viewType) {
            R.layout.item_repo -> RepositoryItemViewHolder(
                reposBinding
            )
            else -> HeaderItemViewHolder(
                ItemHeaderRepoBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

    class RepositoryItemViewHolder(
        private val binding: ItemRepoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepoBaseVo.RepositoryVo) {
            with(binding) {
                binding.item = item
                repoName.text = item.name
                userName.text = item.username
                avatar.loadUrl(item.userImageUrl)
                description.text = item.description
                metricsInfo.text = item.metricsInfo
            }
        }
    }

    class HeaderItemViewHolder(
        private val binding: ItemHeaderRepoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepoBaseVo.Header) {
            with(binding) {
                title.setText(item.titleRes)
            }
        }
    }
}
