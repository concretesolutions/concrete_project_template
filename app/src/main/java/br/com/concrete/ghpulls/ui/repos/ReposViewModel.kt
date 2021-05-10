package br.com.concrete.ghpulls.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.ghpulls.pagingsource.LoadReposPagingSource
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
    }.flow.cachedIn(viewModelScope).map {
        it.map { repo -> reposMapper.mapModelToVo(repo) }
    }

}