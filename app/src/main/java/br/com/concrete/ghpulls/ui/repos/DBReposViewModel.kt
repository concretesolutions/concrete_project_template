package br.com.concrete.ghpulls.ui.repos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.concrete.database.DBReposService
import br.com.concrete.database.dao.FavoritesDao
import br.com.concrete.database.entity.RepositoryEntity
import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBReposViewModel (
    private val favoritesDao: FavoritesDao,
    private val reposMapper: ReposMapper,
) : ViewModel() {

    var listAllFavs: List<RepositoryEntity> = listOf()

    fun getAllFavs(): List<RepositoryEntity> {
//        var allFavs: List<RepositoryEntity> = listOf()
        viewModelScope.launch(Dispatchers.IO) {
            listAllFavs = favoritesDao.getAllFavorites()
            Log.d(TAG, "favs size : " + listAllFavs.size)
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            listAllFavs = favoritesDao.getAllFavorites()
            Log.d(TAG, "init fav size : " + listAllFavs.size)
        }
    }

    companion object {
        const val TAG = "DBReposViewModel"
    }

}