package br.com.concrete.ghpulls.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.pagingsource.LoadReposPagingSource
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.network.GithubService
import kotlinx.coroutines.flow.map

class ReposViewModel(
    githubService: GithubService,
    reposMapper: ReposMapper,
) : ViewModel() {

    val kotlinReposPager = Pager(
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE)
    ) {
        LoadReposPagingSource { pageNumber ->
            githubService.getRepos(pageNumber).repositories
        }
    }.flow
        .map { it.map { repo -> reposMapper.mapModelToVo(repo) } }
        .map {
            it.insertSeparators { before, _ ->
                when (before) {
                    null -> {
                        RepoBaseVo.Header(R.string.others)
                    }
                    else -> null
                }
            }
        }
        .cachedIn(viewModelScope)

}