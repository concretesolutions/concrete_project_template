package br.com.concrete.ghpulls.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.database.dao.FavoritesDao
import br.com.concrete.database.entity.RepositoryEntity
import br.com.concrete.database.entity.RepositoryEntity.Companion.toModel
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DBReposViewModel(
    private val favoritesDao: FavoritesDao,
    private val reposMapper: ReposMapper,
) : ViewModel() {
    private val _refreshNetworkDb = MutableLiveData<Unit>()
    val refreshNetworkDb: LiveData<Unit> = _refreshNetworkDb

    val kotlinDBReposPager = Pager(
        config = PagingConfig(DEFAULT_PAGE_SIZE)
    ) {
        favoritesDao.getAllFavorites()
    }.flow
        .map {
            val favoriteIds = favoritesDao.getIdAllFavorites()
            it.map { repo ->
                reposMapper.mapModelToVo(
                    repo.toModel(),
                    favoriteIds
                )
            }
        }
        .map {
            it.insertSeparators { before, _ ->
                if (before == null) {
                    RepoBaseVo.Header(R.string.favorites)
                } else {
                    null
                }
            }
        }
        .cachedIn(viewModelScope)

    fun favoriteUnfavoriteRepository(repositoryVo: RepoBaseVo.RepositoryVo) {
        viewModelScope.launch {
            val repo = RepositoryEntity.fromModel(repositoryVo.repositoryModel)
            if (repositoryVo.isFavorite) {
                favoritesDao.remove(repo)
            } else {
                favoritesDao.insert(repo)
            }

            _refreshNetworkDb.value = Unit
        }
    }
}