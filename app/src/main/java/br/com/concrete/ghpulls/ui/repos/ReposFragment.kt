package br.com.concrete.ghpulls.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.databinding.FragmentReposBinding
import br.com.concrete.ghpulls.util.ItemVerticalSpaceDecorator
import br.com.concrete.ghpulls.util.adapter.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ReposFragment : Fragment() {

    private val reposViewModel: ReposViewModel by inject()

    private lateinit var binding: FragmentReposBinding
    private val reposAdapter = ReposAdapter()
    private val loadState = LoadStateAdapter {
        reposAdapter.retry()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.reposList.adapter = reposAdapter.withLoadStateFooter(loadState)
        binding.reposList.addItemDecoration(
            ItemVerticalSpaceDecorator(R.dimen.default_margin)
        )

        binding.errorTryAgainButton.setOnClickListener {
            reposAdapter.retry()
        }

        lifecycleScope.launch {
            reposViewModel.kotlinReposPager.collectLatest {
                reposAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            reposAdapter.loadStateFlow
                .collectLatest { loadState ->
                    if (loadState.refresh is LoadState.Loading) {
                        binding.progress.show()
                    } else {
                        binding.progress.hide()
                    }

                    binding.errorGroup.isVisible = loadState.refresh is LoadState.Error
                }
        }
    }
}