package br.com.concrete.ghpulls.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.database.dao.FavoritesDao
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.pagingsource.LoadReposPagingSource
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.network.GithubService
import kotlinx.coroutines.flow.map

class ReposViewModel(
    githubService: GithubService,
    reposMapper: ReposMapper,
    favoritesDao: FavoritesDao
) : ViewModel() {
    val kotlinReposPager = Pager(
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE)
    ) {
        LoadReposPagingSource { pageNumber ->
            githubService.getRepos(pageNumber).repositories
        }
    }.flow
        .map {
            val favoriteIds = favoritesDao.getIdAllFavorites()
            it.filter { repository ->
                repository.id !in favoriteIds
            }.map { repo -> reposMapper.mapModelToVo(repo, emptyList()) }
        }
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
}