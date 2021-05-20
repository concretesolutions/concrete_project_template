package br.com.concrete.ghpulls.ui.repos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import br.com.concrete.base.DEFAULT_PAGE_SIZE
import br.com.concrete.database.DBReposService
import br.com.concrete.database.dao.FavoritesDao
import br.com.concrete.database.entity.RepositoryEntity
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.pagingsource.LoadReposPagingSource
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DBReposViewModel (
    private val favoritesDao: FavoritesDao,
    private val reposMapper: ReposMapper,
) : ViewModel() {

    var listAllFavs: PagingSource<Int,RepositoryEntity> = getAllFavs()

    fun getAllFavs(): PagingSource<Int, RepositoryEntity> {
        viewModelScope.launch(Dispatchers.IO) {
            listAllFavs = favoritesDao.getAllFavorites()
//            Log.d(TAG, "getAllFavs() favs size : " + listAllFavs.size)
        }
        return listAllFavs
    }

    fun insert(repoVo: RepoBaseVo.RepositoryVo) {
        // Create a new coroutine to move the execution off the UI thread
        viewModelScope.launch(Dispatchers.IO) {
            if (favoritesDao.getFavById(repoVo.id) == null)
                favoritesDao.insert(reposMapper.mapVoToModel(repoVo))
        }
    }

    val kotlinDBReposPager = Pager(
        config =  PagingConfig(DEFAULT_PAGE_SIZE)
//        ,
//        remoteMediator = itemRemoteMediator
    ){
        listAllFavs
    }.flow
        .map { it.map { repo -> reposMapper.mapModelToVo(repo) } }
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

    init {
        getAllFavs()
    }


    companion object {
        const val TAG = "DBReposViewModel"
    }

}