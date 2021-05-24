package br.com.concrete.ghpulls.ui.repos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.database.dao.FavoritesDao
import br.com.concrete.database.entity.RepositoryEntity
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.pagingsource.LoadReposPagingSource
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import br.com.concrete.network.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ReposViewModel(
    githubService: GithubService,
    reposMapper: ReposMapper,
    favoritesDao: FavoritesDao
) : ViewModel() {
    private lateinit var storedDbFav: RepositoryEntity

    val kotlinReposPager = viewModelScope.launch(Dispatchers.IO) {
        Pager(
            PagingConfig(pageSize = DEFAULT_PAGE_SIZE)
        ) {
            LoadReposPagingSource { pageNumber ->
                githubService.getRepos(pageNumber).repositories
            }
        }.flow
        .map {
            it.map {
                repo -> reposMapper.mapRepositoryToVo(repo);
                android.util.Log.d(
                    br.com.concrete.ghpulls.ui.repos.ReposViewModel.Companion.TAG,
                    "repo.id : " + repo.id
                );
                android.util.Log.d(
                    br.com.concrete.ghpulls.ui.repos.ReposViewModel.Companion.TAG,
                    "repo.name : " + repo.name
                );
                storedDbFav = favoritesDao.getFavById(repo.id)!!;
//                val repo1 = reposMapper.mapRepositoryToVo(repo);
//                repo -> reposMapper.mapRepositoryToVo(repo1)
            }
        }
        .map {
            it.map { repo ->
                reposMapper.mapVoToModel(repo)
            }
        }
/*        .map { it.map {
//                repo -> reposMapper.mapVoToModel(repo)
            repo -> repo as RepoBaseVo.RepositoryVo
            storedDbFav = favoritesDao.getFavById(repo.id)!!
            android.util.Log.d(br.com.concrete.ghpulls.ui.repos.ReposViewModel.Companion.TAG, "storedDbFav : " + storedDbFav.name)
        }
    }*/
        .map {
            it.insertSeparators { before, _ ->
                if (before == null) {
                    RepoBaseVo.Header(R.string.others)
                } else if (storedDbFav != null) {
                    RepoBaseVo.Header(R.string.favorites)
                } else {
                    null
                }
            }
        }
        .cachedIn(viewModelScope)
    }

    companion object {
        const val TAG = "ReposViewModel"
    }
}