package br.com.concrete.ghpulls.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.pagingsource.LoadReposPagingSource
import br.com.concrete.ghpulls.ui.repos.ReposMapper
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.network.GithubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchViewModel(
    private val githubService: GithubService,
    private val reposMapper: ReposMapper
) : ViewModel() {

    private val _pager = MutableLiveData<Flow<PagingData<RepoBaseVo>>>()
    val pager: LiveData<Flow<PagingData<RepoBaseVo>>> = _pager

    fun search(query: String) {
        val kotlinReposSearchPager = Pager(
            PagingConfig(pageSize = DEFAULT_PAGE_SIZE)
        ) {
            LoadReposPagingSource { pageNumber ->
                githubService.getRepos(pageNumber, query).repositories
            }
        }.flow
            .map { it.map { repo -> reposMapper.mapModelToVo(repo) } }
            .map {
                it.insertSeparators { before, _ ->
                    if (before == null) {
                        RepoBaseVo.Header(R.string.others)
                    } else {
                        null
                    }
                }
            }
            .cachedIn(viewModelScope)
        _pager.value = kotlinReposSearchPager
    }
}
