package br.com.concrete.ghpulls.ui.repos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.databinding.FragmentReposBinding
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.ghpulls.util.ItemVerticalSpaceDecorator
import br.com.concrete.ghpulls.util.adapter.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ReposFragment : Fragment() {

    private val reposViewModel: ReposViewModel by inject()
    private val dbReposViewModel: DBReposViewModel by inject()

    private lateinit var binding: FragmentReposBinding

    private val clickAction: (RepoBaseVo.RepositoryVo) -> Unit = { repositoryVo ->
        onFavoriteUnfavoriteRepo(repositoryVo)
    }
    private val reposDBAdapter = ReposAdapter(clickAction)
    private val reposAdapter = ReposAdapter(clickAction)

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

    private fun onFavoriteUnfavoriteRepo(repositoryVo: RepoBaseVo.RepositoryVo) {
        dbReposViewModel.favoriteUnfavoriteRepository(repositoryVo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.reposList.adapter = ConcatAdapter(
            reposDBAdapter,
            reposAdapter.withLoadStateFooter(loadState)
        )
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
            dbReposViewModel.kotlinDBReposPager.collectLatest {
                reposDBAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            reposAdapter.loadStateFlow
                .collectLatest { loadState ->
                    if (loadState.refresh is LoadState.Loading) {
                        binding.progress.show()
                        Log.d(TAG, "SHOW PROGRESS")
                    } else {
                        binding.progress.hide()
                        Log.d(TAG, "HIDE PROGRESS")
                    }

                    binding.errorGroup.isVisible = loadState.refresh is LoadState.Error
                }
        }

        dbReposViewModel.refreshNetworkDb.observe(viewLifecycleOwner) {
            reposAdapter.refresh()
        }
    }

    companion object {
        const val TAG = "ReposFragment"
    }
}