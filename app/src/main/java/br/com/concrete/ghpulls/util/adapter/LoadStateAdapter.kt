package br.com.concrete.ghpulls.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.ghpulls.databinding.ItemLoadStateBinding

class LoadStateAdapter(
    private val retryAction: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retryAction
        )
}

class LoadStateViewHolder(
    binding: ItemLoadStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val binding = ItemLoadStateBinding.bind(itemView)

    init {
        binding.tryAgain.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        binding.progressState.visibility =
            if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
        binding.errorState.visibility =
            if (loadState is LoadState.Error) View.VISIBLE else View.GONE
    }
}
