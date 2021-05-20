package br.com.concrete.ghpulls.ui.repos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.databinding.FragmentReposBinding
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.ghpulls.util.ItemVerticalSpaceDecorator
import br.com.concrete.ghpulls.util.adapter.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ReposFragment : Fragment() {

    private val reposViewModel: ReposViewModel by inject()
    private val dbReposViewModel: DBReposViewModel by inject()

    private lateinit var binding: FragmentReposBinding
    private lateinit var reposAdapter: ReposAdapter
    private lateinit var reposDBAdapter: ReposFavsAdapter
    lateinit var adapter: ConcatAdapter
    private val loadState = LoadStateAdapter {
        reposAdapter.retry()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReposBinding.inflate(inflater, container, false)
        reposAdapter = ReposAdapter(reposClickCallback)
        reposDBAdapter = ReposFavsAdapter(reposClickCallback)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        val listOfAdapters = listOf(
            reposDBAdapter,
            reposAdapter.withLoadStateFooter(loadState))
        adapter = ConcatAdapter(listOfAdapters)

        binding.reposList.adapter = adapter

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
                    } else {
                        binding.progress.hide()
                    }

                    binding.errorGroup.isVisible = loadState.refresh is LoadState.Error
                }
        }
    }

    private val reposClickCallback: ReposClickCallback = object : ReposClickCallback {
        override fun onClick(item: RepoBaseVo.RepositoryVo?) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Log.d(TAG, "repo name : " + item?.name)

                if (item != null) {
                    dbReposViewModel.insert(item)
                    dbReposViewModel.getAllFavs()
                }
//                Log.d(TAG, "fav size : " + dbReposViewModel.getAllFavs().size)
            }
        }
    }

    companion object {
        const val TAG = "ReposFragment"
    }
}