package br.com.concrete.ghpulls.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.ghpulls.databinding.ItemRepoBinding
import br.com.concrete.ghpulls.ui.repos.vo.RepositoryVo

class ReposAdapter :
    PagingDataAdapter<RepositoryVo, ReposAdapter.ViewHolder>(RepositoryVo.diffUtil) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class ViewHolder(
        private val binding: ItemRepoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepositoryVo) {
            with(binding) {
                title.text = item.name
            }
        }
    }
}
